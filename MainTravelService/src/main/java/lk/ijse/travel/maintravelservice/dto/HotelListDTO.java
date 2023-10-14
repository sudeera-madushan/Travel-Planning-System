package lk.ijse.travel.maintravelservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lk.ijse.travel.maintravelservice.entity.SuperEntity;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelListDTO extends SuperEntity {

    private String id,bookingId,hotelId;

    public HotelListDTO(String bookingId, String hotelId) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
    }
}
