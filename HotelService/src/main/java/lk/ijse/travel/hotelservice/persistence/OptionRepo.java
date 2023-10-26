package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.HotelOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface OptionRepo extends CrudRepository<HotelOption,String> {
}
