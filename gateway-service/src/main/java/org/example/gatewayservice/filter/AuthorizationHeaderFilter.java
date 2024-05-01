package org.example.gatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final SecretKey secretKey;
    private final Environment env;

    public AuthorizationHeaderFilter(Environment env, @Value(("${spring.jwt.secret}")) String secret) {
        super(Config.class);
        this.env = env;
        secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange.getRequest());

            if (token == null) return onError(exchange, "No JWT token found in request cookie", HttpStatus.UNAUTHORIZED);
            if (!isJwtValid(token)) return onError(exchange, "expired JWT Token", HttpStatus.UNAUTHORIZED);

            return chain.filter(exchange);
        };
    }

    private boolean isJwtValid(String token) {
        log.info("[JwtToken] 유효성 체크");
        return !Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    private String extractToken(ServerHttpRequest request) {
        String token = null;
        if (request.getCookies().containsKey("access")) {
            token = request.getCookies().getFirst("access").getValue();
        }
        return token;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    public static class Config{

    }
}
