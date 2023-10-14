package lk.ijse.travel.hotelservice.bo.impl;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.util.Converter;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.persistence.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private Converter converter;
    @Override
    public Response<HotelDTO> saveHotel(HotelDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Hotel save successfully",
                converter.getHotelDTO(hotelRepo.save(converter.getHotelEntity(dto))));
    }

    @Override
    public Response<HotelDTO> getHotel(String id) {
        return new Response<>(HttpStatus.OK,"Hotel get successfully" ,
                converter.getHotelDTO(hotelRepo.findById(id).get()));
    }
}
