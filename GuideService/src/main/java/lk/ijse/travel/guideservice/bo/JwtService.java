package lk.ijse.travel.guideservice.bo;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : TravelPlanningSystem
 */
public interface JwtService {
    String extractUserName(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Map<String, Object> claims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isValid(String token);

    Claims getClaims(String token);

    List<GrantedAuthority> getUserDetails(String token);
}
