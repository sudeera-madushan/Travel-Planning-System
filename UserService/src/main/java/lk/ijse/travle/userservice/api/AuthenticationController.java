package lk.ijse.travle.userservice.api;


import lk.ijse.travle.userservice.bo.AuthenticationService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/8/2023
 * @Project : TravelPlanningSystem
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @ResponseBody
    @PostMapping
    public Response<String> authenticate(@RequestBody UserDTO user){
        return authenticationService.authenticate(user);
    }

    @ResponseBody
    @GetMapping(value = "{token}")
    public String authenticate(@PathVariable String token){
        return authenticationService.checkAuth(token);
    }
}
