package com.jing.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class MyRouteGlobalFilter implements GlobalFilter, Ordered {
    private static final String START_VISIT_TIME = "startVisitTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(START_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_VISIT_TIME);
            if (startTime != null) {
                URI uri = exchange.getRequest().getURI();
                log.info("访问接口主机:{}" , uri.getHost());
                log.info("访问接口端口:{}" , uri.getPort());
                log.info("访问接口URL:{}" , uri.getPath());
                log.info("访问接口URL参数:{}" , uri.getRawQuery());
                log.info("访问接口时长:{}" , System.currentTimeMillis() - startTime);
                log.info("================================");
            }


        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
