package com.jing.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder().group("其他微服务模块").pathsToMatch("/other/**", "/others").build();
    }

    @Bean
    public OpenAPI docsOpenAPI() {
        return new OpenAPI().info(new Info().title("cloud").description("SpringCouldTest").version("V1.0"))
                .externalDocs(new ExternalDocumentation().description("jingleCode.top").url("https://jingleCode.top"));
    }
}
