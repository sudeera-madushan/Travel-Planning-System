package lk.ijse.travel.maintravelservice.security;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.travel.maintravelservice.bo.JwtService;
import lk.ijse.travel.maintravelservice.dto.Error;
import lk.ijse.travel.maintravelservice.dto.LocalDateTimeAdapter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/16/2023
 * @Project : TravelPlanningSystem
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final WebClient.Builder webClientBuilder;
    @Autowired
    private JwtService jwtService;
    public JwtAuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userName;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            WebClient userClient = webClientBuilder.baseUrl("http://localhost:8091/travel/api/v1")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt).build();
            String userName1 = userClient.get()
                    .uri("/auth/"+jwt)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            if (userName1 != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userName1, null, jwtService.getUserDetails(jwt));
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Username");
            }
        } catch (Exception ex) {
            sendError(response, request, ex.getMessage());
        }
    }

    @SneakyThrows
    private void sendError(HttpServletResponse response, HttpServletRequest request, String message) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(new Error(HttpStatus.PRECONDITION_FAILED, message, request.getContextPath(),
                LocalDateTime.now())));
    }
}