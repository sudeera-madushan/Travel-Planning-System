package lk.ijse.travle.userservice.api;

import lk.ijse.travle.userservice.bo.UserService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private Response<UserDTO> saveUser(@RequestBody UserDTO user){
        user.setRole(Type.ROLE_ADMIN);
        return userService.save(user);
    }
    @PostMapping("role")
    private Response<String> addRoleList(){
        return userService.addRoleList();
    }
}
