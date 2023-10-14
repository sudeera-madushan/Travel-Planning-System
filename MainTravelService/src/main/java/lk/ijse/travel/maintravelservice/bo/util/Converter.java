package lk.ijse.travel.maintravelservice.bo.util;

import lk.ijse.travel.maintravelservice.dto.*;
import lk.ijse.travel.maintravelservice.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
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
    public PackageCategory getPackageCategoryEntity(PackageCategoryDTO dto){
        return modelMapper.map(dto, PackageCategory.class);
    }
    public PackageCategoryDTO getPackageCategoryDTO(PackageCategory entity){
        return modelMapper.map(entity, PackageCategoryDTO.class);
    }
    public Area getAreaEntity(AreaDTO dto){
        return modelMapper.map(dto, Area.class);
    }
    public AreaDTO getAreaDTO(Area entity){
        return modelMapper.map(entity, AreaDTO.class);
    }
    public AreaImage getAreaImageEntity(AreaImageDTO dto){
        return modelMapper.map(dto, AreaImage.class);
    }
    public AreaImageDTO getAreaImageDTO(AreaImage entity){
        return modelMapper.map(entity, AreaImageDTO.class);
    }
    public List<AreaImageDTO> getAreaImageDTO(List<AreaImage> entitys){
        ArrayList<AreaImageDTO> dtos = new ArrayList<>();
        for (AreaImage entity : entitys) {
            dtos.add(new AreaImageDTO(
                    entity.getId(),
                    entity.getAreaId(),
                    Base64.getDecoder().decode(entity.getImage())
            ));
        }
        return dtos;
    }

    public BookingDTO getBookingDTO(Booking entity){return modelMapper.map(entity, BookingDTO.class);}
    public Booking getBookingEntity(BookingDTO dto){return modelMapper.map(dto, Booking.class);}
    public HotelListDTO getHotelListDTO(HotelList dto){return modelMapper.map(dto, HotelListDTO.class);}
    public List<HotelList> getHotelListEntity(List<HotelListDTO> list) {
        return list.stream().map(dto -> modelMapper.map(dto, HotelList.class)).toList();
    }
    public List<HotelListDTO> getHotelListDTO(Iterable<HotelList> list) {
        List<HotelListDTO> hotels=new ArrayList<>();
        list.forEach(dto -> hotels.add(modelMapper.map(dto, HotelListDTO.class)));
        return hotels;
    }


}
