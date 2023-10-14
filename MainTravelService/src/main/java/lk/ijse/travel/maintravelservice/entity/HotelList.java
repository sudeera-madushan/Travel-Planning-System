package lk.ijse.travel.maintravelservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_list")
public class HotelList extends SuperEntity{
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "hotel_id")
    private String hotelId;
}
