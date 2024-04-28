package com.xsj.xuapiinterface.controller;


import com.xu.xuapiclientsdk.model.User;
import com.xu.xuapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称API
 *
 * @author 徐思捷
 */
@RestController()
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getName(String name, HttpServletRequest request) {

        return "GET 你的名字是 "+ name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是 "+ name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String secreKey = request.getHeader("secreKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        // todo 实际情况应该是数据库中查是否已分配给用户
        if (!accessKey.equals("yupi")) {
            throw new  RuntimeException("非法访问");
        }
        if (Long.parseLong(nonce) > 10000) {
            throw new  RuntimeException("非法访问");
        }
        // todo 时间和当前时间不能超过五分钟

        // todo 实际情况中是从数据库中查出 secretKey
        String serverSign = SignUtils.getSign(body, "abcdefgh");
        if (!sign.equals(serverSign)) {
            throw new  RuntimeException("非法访问");
        }
        String result = "POST 用户名字是 "+ user.getUsername();
        // 调用成功后，次数 + 1
        return result;
    }
}
