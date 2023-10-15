package lk.ijse.travle.userservice.bo;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/7/2023
 * @Project : TravelPlanningSystem
 */
public interface JwtService {
    String extractUserName(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Map<String, Object> claims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
