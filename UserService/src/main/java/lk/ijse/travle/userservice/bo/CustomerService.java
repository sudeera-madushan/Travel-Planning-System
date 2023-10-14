package lk.ijse.travle.userservice.bo;

import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.CustomerDTO;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */

public interface CustomerService {
    Response<CustomerDTO> save(CustomerDTO user);
    Response<CustomerDTO> get(String id);
}
