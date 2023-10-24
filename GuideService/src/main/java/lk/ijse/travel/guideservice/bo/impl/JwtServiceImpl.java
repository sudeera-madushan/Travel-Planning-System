package lk.ijse.travel.guideservice.bo.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.travel.guideservice.bo.JwtService;
import lk.ijse.travel.guideservice.dto.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : TravelPlanningSystem
 */
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${spring.jwt.secrete}")
    private String secrete;



    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.
                builder().
                setClaims(claims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + (1800000000))).
                signWith(getSigninKey(), SignatureAlgorithm.HS256).
                compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secrete) // Use the same secret key as the authentication service
                    .parseClaimsJws(token)
                    .getBody();

            // Check if the token is expired (optional)
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            // Token is invalid or expired
            return false;
        }
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secrete)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate == null || expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secrete);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public List<GrantedAuthority> getUserDetails(String token) {
        List<GrantedAuthority> authorities=new ArrayList<>();
        try {
            Claims claims = getClaims(token);
//            String subject = (String) claims.get(Claims.SUBJECT);
            ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
            for (String aRoleName : roles) {
                authorities.add(new Auth(aRoleName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return authorities;
    }

}
