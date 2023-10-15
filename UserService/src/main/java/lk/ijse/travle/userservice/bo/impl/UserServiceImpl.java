package lk.ijse.travle.userservice.bo.impl;

import lk.ijse.travle.userservice.bo.UserService;
import lk.ijse.travle.userservice.bo.util.Converter;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.security.Auth;
import lk.ijse.travle.userservice.entity.security.Role;
import lk.ijse.travle.userservice.entity.security.User;
import lk.ijse.travle.userservice.persistence.RoleRepo;
import lk.ijse.travle.userservice.persistence.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Converter converter;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public Response<UserDTO> save(UserDTO user) {
        User userEntity = converter.getUserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        User saveUser = userRepo.save(userEntity);
        saveUser.getAuths().add(new Auth(saveUser,roleRepo.findByType(user.getRole())));
        return new Response<>(HttpStatus.CREATED,"User save successfully",
                converter.getUserDTO(saveUser));
    }

    @Override
    public User findUserNameByUserName(String username) {
        return null;
    }

    @Override
    public Response<String> addRoleList() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("admin", Type.ROLE_ADMIN));
        roles.add(new Role("user", Type.ROLE_USER));
        roles.add(new Role("hotel admin", Type.ROLE_HOTEL));
        roles.add(new Role("guide admin", Type.ROLE_GUIDE));
        roles.add(new Role("vehicle admin", Type.ROLE_VEHICLE));
        roles.add(new Role("booking admin", Type.ROLE_BOOKING));
        roleRepo.saveAll(roles);
        return new Response<>(HttpStatus.CREATED,"Role list added successfully",
                "Role list added successfully");
    }

//    public UserDTO findUserById(int id){
//        return userRepo.findById(id).get();
//    }

}
