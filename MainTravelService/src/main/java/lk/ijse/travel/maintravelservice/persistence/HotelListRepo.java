package lk.ijse.travel.maintravelservice.persistence;

import lk.ijse.travel.maintravelservice.entity.HotelList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface HotelListRepo extends CrudRepository<HotelList,String> {
    List<HotelList> findAllByBookingIdEqualsIgnoreCase(String id);
}
