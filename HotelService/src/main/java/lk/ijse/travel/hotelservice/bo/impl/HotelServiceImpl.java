package lk.ijse.travel.hotelservice.bo.impl;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.util.Converter;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.HotelOptionDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.HotelImage;
import lk.ijse.travel.hotelservice.entity.HotelOption;
import lk.ijse.travel.hotelservice.persistence.HotelImageRepo;
import lk.ijse.travel.hotelservice.persistence.HotelOptionRepo;
import lk.ijse.travel.hotelservice.persistence.HotelRepo;
import lk.ijse.travel.hotelservice.persistence.OptionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final OptionRepo optionRepo;
    private final HotelOptionRepo hotelOptionRepo;
    private final Converter converter;
//    @Transactional
    @Override
    public Response<HotelDTO> saveHotel(HotelDTO dto) {
        List<HotelImageDTO> hotelImages = dto.getHotelImages();
        List<HotelOptionDTO> optionList = dto.getOptions();
        dto.setHotelImages(new ArrayList<>());
        Hotel entity = converter.getHotelEntity(dto);
        entity.setOptions(new ArrayList<>());
        for (HotelOptionDTO option : optionList) {
            entity.getOptions().add(new HotelOption(
                    entity,
                    optionRepo.findHotelOptionByNameIgnoreCase(
                            option.getName()
                    ),
                    option.getCharge()
                    ));
        }
        Hotel hotel = hotelRepo.save(entity);
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

    @Transactional
    @Override
    public Response<String> deleteHotelById(String id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);
        if (hotel.isPresent()) {
            hotelOptionRepo.deleteAllByHotel_Id(id);
            hotelImageRepo.deleteHotelImageByHotel(hotel.get());
            hotelRepo.deleteById(id);
            return new Response<>(HttpStatus.OK, "Hotel Delete Successfully", id);
        }
        return new Response<>(HttpStatus.NOT_FOUND, "Hotel Not Found", id);
    }
}
