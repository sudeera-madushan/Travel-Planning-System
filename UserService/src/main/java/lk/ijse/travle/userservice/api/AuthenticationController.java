package lk.ijse.travle.userservice.api;


import lk.ijse.travle.userservice.bo.AuthenticationService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Token;
import lk.ijse.travle.userservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/8/2023
 * @Project : TravelPlanningSystem
 */
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @ResponseBody
    @PostMapping
    public Response<Token> authenticate(@RequestBody UserDTO user){
        return authenticationService.authenticate(user);
    }

    @ResponseBody
    @GetMapping(value = "{token}")
    public String authenticate(@PathVariable String token){
        return authenticationService.checkAuth(token);
    }



    @RequestMapping("log")
    @ResponseBody
    @GetMapping
    public Response<List<String>> getRoles(@RequestParam String token){
        return authenticationService.getRoles(token);
    }
}
