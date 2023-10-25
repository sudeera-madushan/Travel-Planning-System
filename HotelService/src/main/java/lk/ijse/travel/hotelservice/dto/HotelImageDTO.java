package lk.ijse.travel.hotelservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.travel.hotelservice.entity.Hotel;
import lk.ijse.travel.hotelservice.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/25/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelImageDTO{
    private String id;
    private byte[] image;
    private HotelDTO hotel;

    public HotelImageDTO(byte[] image) {
        this.image = image;
    }
}
