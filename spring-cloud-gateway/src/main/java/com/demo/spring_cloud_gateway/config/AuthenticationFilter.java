package com.demo.spring_cloud_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {
    private final WebClient.Builder webClientBuilder;

    @Value("${service.url}")
    private String authServiceUrl;


    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        log.info(token);
        String tokenValue = token.substring(7); // Remove "Bearer " prefix
        return webClientBuilder.build()
                .post()
                .uri(authServiceUrl + "validate")// VALIDATION ENDPOINT
                .body(BodyInserters.fromValue(tokenValue))
                .retrieve()
                .bodyToMono(Boolean.class)
                .flatMap(isValid -> {
                    if (isValid) {
                        return chain.filter(exchange);
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                })
                .onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    log.info(e.getMessage());
                    return exchange.getResponse().setComplete();
                });
    }

    /*MAKE THE FILTERING HIGHEST PRIORITY*/
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE; //Do fist thing first (in your case -1 will work the same way)
    }

}