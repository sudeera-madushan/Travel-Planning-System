package lk.ijse.travel.hotelservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.SuperEntity;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDTO{
    private String id,type;
    private byte[] imageData;
    private HotelDTO hotel;

    public RoomTypeDTO(String type, byte[] imageData, HotelDTO hotel) {
        this.type = type;
        this.imageData = imageData;
        this.hotel = hotel;
    }
}
