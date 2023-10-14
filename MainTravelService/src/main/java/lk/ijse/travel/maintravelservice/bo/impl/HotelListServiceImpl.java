package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.HotelListService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.dto.HotelListDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lk.ijse.travel.maintravelservice.persistence.HotelListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class HotelListServiceImpl implements HotelListService {
    @Autowired
    private Converter converter;
    @Autowired
    private HotelListRepo hotelListRepo;
    @Override
    public Response<List<HotelListDTO>> saveHotelList(List<HotelListDTO> list) {
        return new Response<>(HttpStatus.CREATED,"Hotel List save successfully",
                converter.getHotelListDTO(
                        hotelListRepo.saveAll(
                                converter.getHotelListEntity(list)
                        )
                ));
    }

    @Override
    public Response<List<HotelListDTO>> getHotelListByBookingId(String id) {
        return new Response<>(HttpStatus.CREATED,"Hotel List get successfully",
                converter.getHotelListDTO(
                        hotelListRepo.findAllByBookingIdEqualsIgnoreCase(id)
                ));
    }
}
