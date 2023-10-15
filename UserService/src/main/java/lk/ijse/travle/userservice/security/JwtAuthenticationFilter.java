package lk.ijse.travle.userservice.security;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.travle.userservice.bo.JwtService;
import lk.ijse.travle.userservice.exeption.Error;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.time.LocalDateTime;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : TravelPlanningSystem
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userName;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            userName = jwtService.extractUserName(jwt);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else throw new lk.ijse.travle.userservice.exeption.ServiceException("Invalid Token", HttpStatus.UNAUTHORIZED);
            } else throw new lk.ijse.travle.userservice.exeption.ServiceException("Missing or Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            sendError(response, request, ex.getMessage());
        }
    }
    @SneakyThrows
    private void sendError(HttpServletResponse response, HttpServletRequest request, String message) {
        Gson gson = new GsonBuilder()
                .create();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(new Error(HttpStatus.PRECONDITION_FAILED, message, request.getContextPath(),
                LocalDateTime.now())));
    }
}
