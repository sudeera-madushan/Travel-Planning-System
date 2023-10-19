package lk.ijse.travel.hotelservice.bo.util;

import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.RoomTypeDTO;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.RoomType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Component
public class Converter {
    @Autowired
    private  ModelMapper modelMapper;
    public Hotel getHotelEntity(HotelDTO dto){
        return modelMapper.map(dto, Hotel.class);
    }
    public HotelDTO getHotelDTO(Hotel entity){
        return modelMapper.map(entity, HotelDTO.class);
    }
    public RoomTypeDTO getRoomTypeDTO(RoomType entity){return modelMapper.map(entity, RoomTypeDTO.class);}
    public RoomType getRoomTypeEntity(RoomTypeDTO dto){return modelMapper.map(dto, RoomType.class);}

}