package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface RoomTypeRepo extends CrudRepository<RoomType,String> {
}
