package lk.ijse.travel.hotelservice.bo;

import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.Response;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface HotelService {
    Response<HotelDTO> saveHotel(HotelDTO dto);
    Response<HotelDTO> getHotel(String id);

    Response<List<HotelDTO>> getAllHotels();

    Response<String> deleteHotelById(String id);

    Response<HotelDTO> updateHotel(HotelDTO hotel);

    Response<List<HotelDTO>> findNearestHotels(String id);

    Response<List<HotelDTO>> getAllHotelsByPackageCategory(String id);
}
