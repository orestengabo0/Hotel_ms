package rca.restapi.year2.hotelbookingms.services;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.jwt")
@Data
public class JwtConfig {
    private int accessTokenExpiration;
    private int refreshTokenExpiration;
}