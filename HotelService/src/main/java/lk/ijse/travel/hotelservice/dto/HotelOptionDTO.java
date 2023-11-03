package lk.ijse.travel.hotelservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class HotelOptionDTO {
    private String id,name;
    @NotNull
    private BigDecimal charge;
    @NotNull
    private List<HotelDTO> hotel=new ArrayList<>();

    public HotelOptionDTO(String id, String name, BigDecimal charge) {
        this.id = id;
        this.name = name;
        this.charge = charge;
    }
}
