package lk.ijse.travle.userservice.persistence;

import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.entity.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface RoleRepo extends CrudRepository<Role,String> {
    Role findByType(Type type);
}
