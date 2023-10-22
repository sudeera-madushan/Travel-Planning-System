package lk.ijse.travle.userservice.bo.impl;

import lk.ijse.travle.userservice.bo.AuthenticationService;
import lk.ijse.travle.userservice.bo.JwtService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Token;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.security.Auth;
import lk.ijse.travle.userservice.entity.security.User;
import lk.ijse.travle.userservice.persistence.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : TravelPlanningSystem
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    @Override
    public Response<Token> authenticate(UserDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        User userEntity = userRepo.findByUsernameIgnoreCase(user.getUsername()).orElseThrow(() -> new BadCredentialsException("User Not Found"));
        HashMap<String, Object> map = new HashMap<>();
        for (Auth auth : userEntity.getAuths()) {
            map.put("roles", auth.getRole().getType());
        }
        return new Response<>(HttpStatus.OK,"Authenticate successfully",
                new Token(
                        jwtService.generateToken(map,userEntity),
                        userEntity.getAuths().stream().map(
                                auth -> auth.getRole().getType()
                        ).toList()));
    }

    @Override
    public String checkAuth(String token) {

        return  userRepo.findByUsernameIgnoreCase(jwtService.extractUserName(token)).orElseThrow(() -> new BadCredentialsException("Invalid Username ")).getUsername();
    }
}
