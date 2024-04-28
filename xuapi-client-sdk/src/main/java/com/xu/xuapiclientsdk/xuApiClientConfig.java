package com.xu.xuapiclientsdk;

import com.xu.xuapiclientsdk.client.XuApiClient;
import com.xu.xuapiclientsdk.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("xuapi.client")
@Data
@ComponentScan
public class xuApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public XuApiClient xuApiClient(){

        return new XuApiClient(accessKey, secretKey);
    }
}
