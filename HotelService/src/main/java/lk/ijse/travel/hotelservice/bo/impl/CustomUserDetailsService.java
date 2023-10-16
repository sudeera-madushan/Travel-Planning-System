package lk.ijse.travel.hotelservice.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/16/2023
 * @Project : TravelPlanningSystem
 */
@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username+"Custome");
        WebClient userClient = webClientBuilder.baseUrl("http://localhost:8091/travel/api/v1/user").build();
        Mono<UserDetails> userDetailsMono = userClient.get()
                .uri("/getUserByUsername?username=" + username)
                .retrieve()
                .bodyToMono(UserDetails.class);

        UserDetails userDetails = userDetailsMono.block();
        if (userDetails != null) {
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}