package lk.ijse.travle.userservice.bo.impl;

import lk.ijse.travle.userservice.bo.CustomerService;
import lk.ijse.travle.userservice.bo.UserService;
import lk.ijse.travle.userservice.bo.util.Converter;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.CustomerDTO;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.Customer;
import lk.ijse.travle.userservice.persistence.CustomerRepo;
import lk.ijse.travle.userservice.persistence.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo userDetailsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    private Converter converter;
    @Transactional
    @Override
    public Response<CustomerDTO> save(CustomerDTO customer) {
//        UserDTO saveUser = userService.save();
//        customer.setUser(saveUser);
        Customer customerEntity = converter.getCustomerEntity(customer);
        customerEntity.setUser(userRepo.findById(customer.getUserId()).get());
        Customer save = userDetailsRepo.save(customerEntity);
        return new Response<>(HttpStatus.CREATED,"User Save Successfully",converter.getCustomerDTO(save));
    }

    @Override
    public Response<CustomerDTO> get(String id) {
        return new Response<>(HttpStatus.OK,"User find successfully",
                converter.getCustomerDTO(userDetailsRepo.findById(id).get()));
    }
}
