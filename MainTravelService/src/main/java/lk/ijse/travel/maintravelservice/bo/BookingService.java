package lk.ijse.travel.maintravelservice.bo;

import lk.ijse.travel.maintravelservice.dto.BookingDTO;
import lk.ijse.travel.maintravelservice.dto.Response;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface BookingService {
    Response<BookingDTO> saveBooking(BookingDTO dto);
    Response<BookingDTO> getBooking(String id);
}
