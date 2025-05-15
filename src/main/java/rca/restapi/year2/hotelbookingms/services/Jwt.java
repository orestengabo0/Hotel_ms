package rca.restapi.year2.hotelbookingms.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import rca.restapi.year2.hotelbookingms.enums.UserRoles;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
@Data
public class Jwt {
    private Claims claims;
    private SecretKey secretKey;

    public boolean isExpired(){
        return claims.getExpiration().before(new Date());
    }

    public Long getUserId(){
        return Long.valueOf(claims.getSubject());
    }

    public UserRoles getRole(){
        return UserRoles.valueOf(claims.get("role", String.class));
    }

    public String toString(){
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
