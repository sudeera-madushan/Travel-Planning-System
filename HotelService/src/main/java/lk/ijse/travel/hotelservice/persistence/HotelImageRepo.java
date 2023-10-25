package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.HotelImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/25/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface HotelImageRepo extends CrudRepository<HotelImage,String> {
}
