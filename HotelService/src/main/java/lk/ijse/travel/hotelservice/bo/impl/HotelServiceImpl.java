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
import java.util.Base64;
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

    @Override
    public Response<HotelDTO> saveHotel(HotelDTO dto) {
        List<HotelImageDTO> hotelImages = dto.getHotelImages();
        List<HotelOptionDTO> optionList = dto.getOptions();
        dto.setHotelImages(new ArrayList<>());
        Hotel entity = converter.getHotelEntity(dto);
        entity.setOptions(new ArrayList<>());
        for (HotelOptionDTO option : optionList) {
            entity.getOptions().add(
                    hotelOptionRepo.save(new HotelOption(
                    entity,
                    optionRepo.findHotelOptionByNameIgnoreCase(
                            option.getName()
                    ),
                    option.getCharge()
            )));
        }
        Hotel hotel = hotelRepo.save(entity);
        for (HotelImage hotelImage : hotelImages.stream().map(converter::getHotelImageEntity).toList()) {
            hotelImage.setHotel(hotel);
            hotel.getHotelImages().add(hotelImageRepo.save(hotelImage));
        }
        return new Response<>(HttpStatus.CREATED, "Hotel save successfully",
                converter.getHotelDTO(hotel));
    }

    @Override
    public Response<HotelDTO> getHotel(String id) {
        return new Response<>(HttpStatus.OK, "Hotel get successfully",
                converter.getHotelDTO(hotelRepo.findById(id).get()));
    }

    @Override
    public Response<List<HotelDTO>> getAllHotels() {
        return new Response<>(HttpStatus.OK, "Get All Hotels Successfully",
                hotelRepo.findAll().stream().map(
                        entity -> {
                            HotelDTO dto = converter.getHotelDTO(entity);
                            return dto;
                        }
                ).toList());
    }

    @Transactional
    @Override
    public Response<String> deleteHotelById(String id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);
        if (hotel.isPresent()) {
            hotelOptionRepo.deleteAllByHotelId(id);
            hotelImageRepo.deleteHotelImageByHotel(hotel.get());
            hotelRepo.deleteById(id);
            return new Response<>(HttpStatus.OK, "Hotel Delete Successfully", id);
        }
        return new Response<>(HttpStatus.NOT_FOUND, "Hotel Not Found", id);
    }
//    @Transactional
    @Override
    public Response<HotelDTO> updateHotel(HotelDTO dto) {
        Optional<Hotel> optional = hotelRepo.findById(dto.getId());
        if (optional.isPresent()) {
            Hotel hotel = getHotel(dto, optional);
            for (int s = 0; s < hotel.getOptions().size(); s++) {
                for (HotelOptionDTO dtoOption : dto.getOptions()) {
                    if (dtoOption.getName().equalsIgnoreCase(hotel.getOptions().get(s).getOption().getName())) {
                        hotel.getOptions().get(s).setCharge(dtoOption.getCharge());
                    }
                }
            }
            for (int i = 0; i < dto.getHotelImages().size(); i++) {
//                if (hotel.getHotelImages().size()>=i) {
//                    hotel.getHotelImages().size()
//                    hotel.getHotelImages().get(i).
//                            setImage(
//                                    Base64.getEncoder().encodeToString(
//                                            dto.getHotelImages().get(i).getImage()
//                                    ));
//                }else {
//                    hotel.getHotelImages().add(
//                            hotelImageRepo.save(new HotelImage(
//                                    Base64.getEncoder().encodeToString(
//                                            dto.getHotelImages().get(i).getImage()
//                                    ),hotel
//                            ))
//                    );
                }

            if (dto.getHotelImages().size()==hotel.getHotelImages().size()) {
                for (int i = 0; i < dto.getHotelImages().size(); i++) {
                    hotel.getHotelImages().get(i).setImage(
                            Base64.getEncoder().encodeToString(dto.getHotelImages().get(i).getImage())
                    );
                    hotelImageRepo.save(hotel.getHotelImages().get(i));
                }
            }else if (dto.getHotelImages().size()>hotel.getHotelImages().size()){
                for (int i = 0; i < dto.getHotelImages().size(); i++) {
                    if (hotel.getHotelImages().size()>i){
                        hotel.getHotelImages().get(i).setImage(
                                Base64.getEncoder().encodeToString(dto.getHotelImages().get(i).getImage())
                        );
                    }else {
                        hotel.getHotelImages().add(
                            hotelImageRepo.save(new HotelImage(
                                    Base64.getEncoder().encodeToString(
                                            dto.getHotelImages().get(i).getImage()
                                    ),hotel
                            ))
                    );
                    }

                }
            }else {
                int size = hotel.getHotelImages().size();
                for (int i = size-1; i > -1; i--) {

                    if (i>dto.getHotelImages().size()-1){
                        HotelImage hotelImage = hotel.getHotelImages().get(i);
                        hotel.getHotelImages().remove(hotelImage);
                        hotelImageRepo.delete(hotelImage);

                    }else {
                        HotelImage hotelImage = hotel.getHotelImages().get(i);
                        hotelImage.setImage(
                                Base64.getEncoder().encodeToString(dto.getHotelImages().get(i).getImage())
                        );
                    }
                }
            }

//            }

//            for (HotelImage hotelImage : dto.getHotelImages().stream().map(converter::getHotelImageEntity).toList()) {
//                hotelImage.setHotel(hotelRepo.save(hotel));
//                hotel.getHotelImages().add(hotelImageRepo.save(hotelImage));
//            }


            return new Response<>(HttpStatus.CREATED, "Hotel Update successfully",
                    converter.getHotelDTO(hotel));

        }
        return new Response<>(HttpStatus.NOT_FOUND, "Hotel Not Found",dto);

    }
    private static Hotel getHotel(HotelDTO dto, Optional<Hotel> optional) {
        Hotel hotel = optional.get();
        hotel.setName(dto.getName());
        hotel.setEmail(dto.getEmail());
        hotel.setCancellationCriteriaIsFree(dto.isCancellationCriteriaIsFree());
        hotel.setLocation(dto.getLocation());
        hotel.setMapLocation(dto.getMapLocation());
        hotel.setCategory(dto.getCategory());
        hotel.setPetIsAllowed(dto.isPetIsAllowed());
        hotel.setCancellationFee(dto.getCancellationFee());
        hotel.setContactNoOne(dto.getContactNoOne());
        hotel.setContactNoTwo(dto.getContactNoTwo());
        hotel.setRemarks(dto.getRemarks());
        return hotel;
    }


//    List<HotelImageDTO> hotelImages = dto.getHotelImages();
//        List<HotelOptionDTO> optionList = dto.getOptions();
//        dto.setHotelImages(new ArrayList<>());
//        Hotel entity = converter.getHotelEntity(dto);
//        entity.setOptions(new ArrayList<>());
//        List<HotelOption> hotelOptions = hotelOptionRepo.findAllByHotel_Id(dto.getId());
//        for (int i = 0; i < hotelOptions.size(); i++) {
//            for (HotelOptionDTO optionDTO : optionList) {
//                HotelOption option = hotelOptions.get(i);
//                if (option.getOption().getName().equalsIgnoreCase(optionDTO.getName())) {
//                    option.setCharge(optionDTO.getCharge());
//                    entity.getOptions().add(option);
//                }
//            }
//        }
////        for (HotelOptionDTO option : optionList) {
////            entity.getOptions().add(new HotelOption(
////                    entity,
////                    optionRepo.findHotelOptionByNameIgnoreCase(
////                            option.getName()
////                    ),
////                    option.getCharge()
////            ));
////        }
//        Hotel hotel = hotelRepo.save(entity);
//        for (HotelImage hotelImage : hotelImages.stream().map(converter::getHotelImageEntity).toList()) {
//            hotelImage.setHotel(hotel);
//            hotel.getHotelImages().add(hotelImageRepo.save(hotelImage));
//        }
//        return new Response<>(HttpStatus.CREATED,"Hotel Update successfully",
//                converter.getHotelDTO(hotel));
//    }
}
