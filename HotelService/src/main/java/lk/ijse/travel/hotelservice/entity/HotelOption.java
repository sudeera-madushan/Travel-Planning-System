package lk.ijse.travel.hotelservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_option")
public class HotelOption extends SuperEntity {
    private String name;
    @ManyToMany(mappedBy = "options")
    private List<Hotel> hotel=new ArrayList<>();
}
