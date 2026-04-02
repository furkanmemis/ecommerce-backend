package com.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.gateway.service.JwtValidationService;

import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final String[] PUBLIC_PATH_PREFIXES = {
            "/api/auth",
            "/swagger-ui",
            "/webjars",
            "/v3/api-docs",
            "/auth/v3/api-docs",
            "/stock/v3/api-docs",
            "/notification/v3/api-docs",
            "/auth/health",
            "/stock/health",
            "/notification/health"
    };

    private final JwtValidationService jwtValidationService;

    public AuthGlobalFilter(JwtValidationService jwtValidationService) {
        this.jwtValidationService = jwtValidationService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        for (String publicPathPrefix : PUBLIC_PATH_PREFIXES) {
            if (path.startsWith(publicPathPrefix)) {
                return chain.filter(exchange);
            }
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            byte[] bytes = "Token not found".getBytes();
            return exchange.getResponse().writeWith(
                    Mono.just(
                            exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(bytes)));
        }

        try {
            String token = authHeader.substring(7);
            jwtValidationService.validate(token);
        } catch (Exception exception) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            byte[] bytes = "Invalid token".getBytes();
            return exchange.getResponse().writeWith(
                    Mono.just(
                            exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(bytes)));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
