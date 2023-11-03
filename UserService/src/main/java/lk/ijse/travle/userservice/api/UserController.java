package lk.ijse.travle.userservice.api;

import jakarta.validation.Valid;
import lk.ijse.travle.userservice.bo.UserService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : Next Travel Pvt. Ltd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private Response<UserDTO> saveUser(@RequestBody @Valid UserDTO user){
        user.getRole().add(Type.ROLE_USER);
        return userService.save(user);
    }
    @PostMapping("role")
    private Response<String> addRoleList(){
        return userService.addRoleList();
    }

    @PostMapping("admin")
    private Response<UserDTO> saveAdmin(@RequestBody UserDTO user){
        return userService.save(user);
    }

    @GetMapping("all")
    private Response<List<UserDTO>> getAll(){
        return userService.getAllUsers();
    }

}
