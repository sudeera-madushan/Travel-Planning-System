package lk.ijse.travel.maintravelservice.bo;

import lk.ijse.travel.maintravelservice.dto.HotelListDTO;
import lk.ijse.travel.maintravelservice.dto.Response;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface HotelListService {
    Response<List<HotelListDTO>> saveHotelList(List<HotelListDTO> list);
    Response<List<HotelListDTO>> getHotelListByBookingId(String id);
}
