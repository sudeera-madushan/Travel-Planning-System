package lk.ijse.travle.userservice.bo;

import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.CustomerDTO;
import lk.ijse.travle.userservice.entity.security.User;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/15/2023
 * @Project : Next Travel Pvt. Ltd
 */

public interface CustomerService {
    Response<CustomerDTO> save(CustomerDTO user);
    Response<CustomerDTO> get(String id);

    Response<CustomerDTO> findByUserId(User user);
}
