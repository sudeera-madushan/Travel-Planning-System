package lk.ijse.travle.userservice.bo.impl;

import lk.ijse.travle.userservice.bo.UserService;
import lk.ijse.travle.userservice.bo.util.Converter;
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
import org.springframework.stereotype.Service;

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
    @Override
    public UserDTO save(UserDTO user) {
        User userEntity = converter.getUserEntity(user);
        User saveUser = userRepo.save(userEntity);
        saveUser.getAuths().add(new Auth(saveUser,roleRepo.findByType(user.getRole())));
        return converter.getUserDTO(saveUser);
    }

    @Override
    public User findUserNameByUserName(String username) {
        return null;
    }

}
