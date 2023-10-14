package lk.ijse.travel.maintravelservice.persistence;

import lk.ijse.travel.maintravelservice.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface BookingRepo extends CrudRepository<Booking,String> {
}
