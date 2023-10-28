package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface OptionRepo extends CrudRepository<Option,String> {
    Option findHotelOptionByNameIgnoreCase(String name);
}
