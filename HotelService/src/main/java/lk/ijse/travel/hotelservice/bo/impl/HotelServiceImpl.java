package lk.ijse.travel.hotelservice.bo.impl;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.util.Converter;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.HotelImage;
import lk.ijse.travel.hotelservice.persistence.HotelImageRepo;
import lk.ijse.travel.hotelservice.persistence.HotelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;
    private final HotelImageRepo hotelImageRepo;
    private final Converter converter;
//    @Transactional
    @Override
    public Response<HotelDTO> saveHotel(HotelDTO dto) {
        List<HotelImageDTO> hotelImages = dto.getHotelImages();
        dto.setHotelImages(new ArrayList<>());
        Hotel hotel = hotelRepo.save(converter.getHotelEntity(dto));
        for (HotelImage hotelImage : hotelImages.stream().map(converter::getHotelImageEntity).toList()) {
            hotelImage.setHotel(hotel);
            hotel.getHotelImages().add(hotelImageRepo.save(hotelImage));
        }
        return new Response<>(HttpStatus.CREATED,"Hotel save successfully",
                converter.getHotelDTO(hotel));
    }

    @Override
    public Response<HotelDTO> getHotel(String id) {
        return new Response<>(HttpStatus.OK,"Hotel get successfully" ,
                converter.getHotelDTO(hotelRepo.findById(id).get()));
    }

    @Override
    public Response<List<HotelDTO>> getAllHotels() {
        return new Response<>(HttpStatus.OK,"Get All Hotels Successfully",
                hotelRepo.findAll().stream().map(
                        entity -> {
                            HotelDTO dto=converter.getHotelDTO(entity);
                           return dto;
                        }
                ).toList());
    }
}
