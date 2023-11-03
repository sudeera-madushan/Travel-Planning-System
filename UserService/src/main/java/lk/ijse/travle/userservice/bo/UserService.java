package lk.ijse.travle.userservice.bo;

import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.security.User;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface UserService {
    Response<UserDTO> save(UserDTO user);

    User findUserNameByUserName(String username);

    Response<String> addRoleList();


    Response<List<UserDTO>> getAllUsers();
}
