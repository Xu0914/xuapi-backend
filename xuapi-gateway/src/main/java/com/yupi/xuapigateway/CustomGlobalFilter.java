package com.yupi.xuapigateway;

import com.xu.xuapiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局过滤
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + request.getPath().value());
        log.info("请求方法：" + request.getMethod());
        log.info("请求参数：" + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求地址：" + sourceAddress);
        log.info("请求地址：" + request.getRemoteAddress());
        ServerHttpResponse response = exchange.getResponse();
        // 2. 访问控制 - 黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            log.info("非法请求：" + sourceAddress);
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        // 3. 用户鉴权（判断 ak、sk 是否合法）
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // todo 实际情况应该是数据库中查是否已分配给用户
        if (!"yupi".equals(accessKey)) {
            return handleNoAuth(response);
        }
        if (Long.parseLong(nonce) > 10000) {
            return handleNoAuth(response);
        }
        // 时间和当前时间不能超过五分钟
        Long currentTime = System.currentTimeMillis() / 1000;
        Long FIVE_MINUTES = 5 * 60L;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return handleNoAuth(response);
        }
        // todo 实际情况中是从数据库中查出 secretKey
        String serverSign = SignUtils.getSign(body, "abcdefgh");
        if (!sign.equals(serverSign)) {
            return handleNoAuth(response);
        }
        // 4. 请求的模拟接口是否存在？
        // todo 从数据库中查询模拟接口是否存在，以及请求方法是否匹配（还可以校验请求参数）
        // 5. 请求转发，调用模拟接口
        Mono<Void> filter = chain.filter(exchange);
        // 6. 响应日志
        return handleResponse(exchange, chain);
        // 7. todo 调用成功，接口调用次数 + 1 invokeCount
//        if (response.getStatusCode() == HttpStatus.OK) {
//
//        } else {
//            // 8. 调用失败，返回一个规范的错误码
//            return handleNoAuth(response);
//        }
//        log.info("custom global filter");
//        return filter;
    }

    /**
     * 处理响应
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓冲数据工厂
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if (statusCode != HttpStatus.OK) {
                return chain.filter(exchange);//降级处理返回数据
            }
            // 装饰，增强能力
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    if (body instanceof Flux) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        // 往返回值里面写数据
                        return super.writeWith(
                            fluxBody.map(dataBuffer -> {
                                // 7. todo 调用成功，接口调用次数 + 1 invokeCount
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8); //data
                                sb2.append(data);
                                // 打印日志
                                log.info("响应结果：" + data);
                                return bufferFactory.wrap(content);
                        }));
                    } else {
                        log.error("<-- {} 响应code异常", getStatusCode());
                    }
                    return super.writeWith(body);
                }
            };
            // 设置response对象为装饰过的
            return chain.filter(exchange.mutate().response(decoratedResponse).build());

        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}