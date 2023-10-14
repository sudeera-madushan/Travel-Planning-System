package lk.ijse.travel.hotelservice.bo;

import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.Response;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface HotelService {
    Response<HotelDTO> saveHotel(HotelDTO dto);
    Response<HotelDTO> getHotel(String id);
}
