package lk.ijse.travel.hotelservice.persistence;

import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.HotelOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/28/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface HotelOptionRepo extends CrudRepository<HotelOption,String> {
    void deleteAllByHotel_Id(String id);
}
