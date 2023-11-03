package lk.ijse.travle.userservice.persistence;

import lk.ijse.travle.userservice.entity.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface UserRepo extends CrudRepository<User,String> {
    Optional<User> findByUsernameIgnoreCase(String username);
    boolean existsUserByUsername(String username);
    List<User> findAll();

}

