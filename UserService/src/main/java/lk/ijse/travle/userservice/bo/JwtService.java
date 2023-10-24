package lk.ijse.travle.userservice.bo;

import io.jsonwebtoken.Claims;
import lk.ijse.travle.userservice.dto.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
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

    List<String> getUserDetails(String token);

    Claims getClaims(String token);
}
