package lk.ijse.travle.userservice.bo.util;

import lk.ijse.travle.userservice.dto.CustomerDTO;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.Customer;
import lk.ijse.travle.userservice.entity.security.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;
    public Customer getCustomerEntity(CustomerDTO user){
        return modelMapper.map(user, Customer.class);
    }
    public CustomerDTO getCustomerDTO(Customer user){
        return modelMapper.map(user, CustomerDTO.class);
    }
    public User getUserEntity(UserDTO user){
        return modelMapper.map(user, User.class);
    }
    public UserDTO getUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
