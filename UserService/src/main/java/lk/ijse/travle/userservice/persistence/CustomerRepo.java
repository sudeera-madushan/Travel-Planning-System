package lk.ijse.travle.userservice.persistence;

import lk.ijse.travle.userservice.entity.Customer;
import lk.ijse.travle.userservice.entity.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface CustomerRepo extends CrudRepository<Customer,String> {
    Customer findCustomerByUser(User user);
}
