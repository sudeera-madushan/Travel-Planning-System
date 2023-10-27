package lk.ijse.travel.hotelservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/27/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HotelOption extends SuperEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel", referencedColumnName = "id")
    private Hotel hotel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "options", referencedColumnName = "id")
    private Option option;
    private BigDecimal charge;


}
