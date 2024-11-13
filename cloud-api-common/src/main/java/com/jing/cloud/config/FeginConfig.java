package com.jing.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {
    @Bean
    public Retryer retryer() {
//        return Retryer.NEVER_RETRY; // 默认不走重试机制
        return new Retryer.Default(100, 1, 3); // 初始间隔100ms, 重试间最大间隔为1s， 最大请求次数3
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
