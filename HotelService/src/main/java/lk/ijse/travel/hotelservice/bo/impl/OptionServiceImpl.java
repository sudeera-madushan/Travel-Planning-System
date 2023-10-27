package lk.ijse.travel.hotelservice.bo.impl;

import lk.ijse.travel.hotelservice.bo.RoomTypeService;
import lk.ijse.travel.hotelservice.bo.util.Converter;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.HotelOptionDTO;
import lk.ijse.travel.hotelservice.persistence.OptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class OptionServiceImpl implements RoomTypeService {
    @Autowired
    private OptionRepo roomTypeRepo;
    @Autowired
    private Converter converter;
    @Override
    public Response<HotelOptionDTO> saveRoomType(HotelOptionDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Hotel Type save successfully",
                converter.getRoomTypeDTO(roomTypeRepo.save(
                        converter.getRoomTypeEntity(dto)
                )));
    }

    @Override
    public Response<HotelOptionDTO> getRoomType(String id) {
        return new Response<>(HttpStatus.CREATED,"Hotel Type get successfully",
                converter.getRoomTypeDTO(roomTypeRepo.findById(id).get()));
    }
}
