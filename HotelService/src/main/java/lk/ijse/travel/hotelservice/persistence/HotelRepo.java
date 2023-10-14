package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface HotelRepo extends CrudRepository<Hotel,String> {
}
