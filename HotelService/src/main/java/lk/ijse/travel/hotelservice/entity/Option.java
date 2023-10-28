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
@Table(name = "options")
public class Option extends SuperEntity {
    private String name;
    @OneToMany(mappedBy = "option", cascade = CascadeType.REMOVE)
    private List<HotelOption> hotelOptions= new ArrayList<>();
}
