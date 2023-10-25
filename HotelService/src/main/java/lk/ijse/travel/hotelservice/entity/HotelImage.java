package lk.ijse.travel.hotelservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
public class HotelImage  extends SuperEntity{
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
