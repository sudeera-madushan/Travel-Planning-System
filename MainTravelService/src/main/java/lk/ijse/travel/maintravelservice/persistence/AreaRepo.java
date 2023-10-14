package lk.ijse.travel.maintravelservice.persistence;

import lk.ijse.travel.maintravelservice.entity.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface AreaRepo extends CrudRepository<Area,String> {
}
