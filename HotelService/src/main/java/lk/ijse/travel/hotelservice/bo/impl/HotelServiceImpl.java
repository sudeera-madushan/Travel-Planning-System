package lk.ijse.travel.hotelservice.bo.impl;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.util.Converter;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.RoomTypeDTO;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.HotelImage;
import lk.ijse.travel.hotelservice.entity.RoomType;
import lk.ijse.travel.hotelservice.persistence.HotelImageRepo;
import lk.ijse.travel.hotelservice.persistence.HotelRepo;
import lk.ijse.travel.hotelservice.persistence.RoomTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final RoomTypeRepo roomTypeRepo;
    private final HotelImageRepo hotelImageRepo;
    private final Converter converter;
    @Transactional
    @Override
    public Response<HotelDTO> saveHotel(HotelDTO dto) {
        List<RoomTypeDTO> roomTypes = dto.getRoomTypes();
        List<HotelImageDTO> hotelImages = dto.getHotelImages();
        ArrayList<RoomType> roomE = new ArrayList<>();
        ArrayList<HotelImage> hotelImagesE = new ArrayList<>();
//        dto.setRoomTypes(null);
//        dto.setRoomTypes(new ArrayList<>());
        Hotel hotel = hotelRepo.save(converter.getHotelEntity(dto));
//        for (RoomType type : roomTypes.stream().map(s -> converter.getRoomTypeEntity(s)).toList()) {
//            type.setHotel(hotel);
//            roomE.add(roomTypeRepo.save(type));
//        }
//        hotel.setRoomTypes(roomE);
//        for (HotelImage hotelImage : hotelImages.stream().map(s -> converter.getHotelImageEntity(s)).toList()) {
//            hotelImage.setHotel(hotel);
//            hotelImagesE.add(hotelImageRepo.save(hotelImage));
//        }
//        hotel.setHotelImages(hotelImagesE);
        return new Response<>(HttpStatus.CREATED,"Hotel save successfully",
                converter.getHotelDTO(hotel));
//        return new Response<>(HttpStatus.CREATED,"Hotel save successfully",dto);
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
                            dto.setHotelImages(null);
                            dto.setRoomTypes(null);
                           return dto;
                        }
                ).toList());
    }
}
