package lk.ijse.travel.hotelservice.bo;

import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.HotelOptionDTO;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface RoomTypeService {
    Response<HotelOptionDTO> saveRoomType(HotelOptionDTO dto);
    Response<HotelOptionDTO> getRoomType(String id);
}
