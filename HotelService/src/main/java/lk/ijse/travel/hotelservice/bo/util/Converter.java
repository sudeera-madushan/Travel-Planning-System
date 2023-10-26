package lk.ijse.travel.hotelservice.bo.util;

import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.HotelOptionDTO;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.HotelImage;
import lk.ijse.travel.hotelservice.entity.HotelOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<HotelImage> hotelImages = entity.getHotelImages();
        List<HotelOption> optionList = entity.getOptions();
        hotelImages.forEach(hotelImage -> hotelImage.setHotel(null));
        optionList.forEach(roomType -> roomType.setHotel(null));
        entity.setHotelImages(hotelImages);
        entity.setOptions(optionList);
        return modelMapper.map(entity, HotelDTO.class);
    }
    public HotelOptionDTO getRoomTypeDTO(HotelOption entity){return modelMapper.map(entity, HotelOptionDTO.class);}
    public HotelOption getRoomTypeEntity(HotelOptionDTO dto){return modelMapper.map(dto, HotelOption.class);}

    public HotelImage getHotelImageEntity(HotelImageDTO dto) {
        return modelMapper.map(dto, HotelImage.class);
    }
}
