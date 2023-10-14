package lk.ijse.travel.hotelservice.bo;

import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.RoomTypeDTO;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface RoomTypeService {
    Response<RoomTypeDTO> saveRoomType(RoomTypeDTO dto);
    Response<RoomTypeDTO> getRoomType(String id);
}
