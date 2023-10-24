package lk.ijse.travle.userservice.bo.impl;

import lk.ijse.travle.userservice.bo.AuthenticationService;
import lk.ijse.travle.userservice.bo.JwtService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Token;
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
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        map.put("roles", userEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        List<Auth> auths = userEntity.getAuths();
//        Type[] types = new Type[auths.size()];
//        for (int i = auths.size() - 1; i >= 0; i--) {
//            types[i]=auths.get(i).getRole().getType();
//        }
////        for (Auth auth : auths) {
//////            map.put(auth.getRole().getName(), auth.getRole().getType());
////            types.add(auth.getRole().getType());
////        }
//        map.put("roles",types);
        map.forEach((key, value) -> {
            System.out.println(key+value);
        });
        return new Response<>(HttpStatus.OK,"Authenticate successfully",
                new Token(
                        jwtService.generateToken(map,userEntity),
                        auths.stream().map(
                                auth -> auth.getRole().getType()
                        ).toList()));
    }

    @Override
    public String checkAuth(String token) {
        return  userRepo.findByUsernameIgnoreCase(jwtService.extractUserName(token)).orElseThrow(() -> new BadCredentialsException("Invalid Username ")).getUsername();
    }

    @Override
    public Response<List<String>> getRoles(String token){

        return new Response<>(HttpStatus.ACCEPTED,checkAuth(token),jwtService.getUserDetails(token));
    }
}
