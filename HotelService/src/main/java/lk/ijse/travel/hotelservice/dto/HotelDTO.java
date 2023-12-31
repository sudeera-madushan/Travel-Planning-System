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
public class HotelDTO {
    private String id;
    @NotNull
    private String name,category,location,email,mapLocation,contactNoOne, contactNoTwo;
    @NotNull
    private boolean petIsAllowed;
    @NotNull
    private boolean cancellationCriteriaIsFree;
    private BigDecimal cancellationFee;
    private String packageCategoryId;
    private List<HotelImageDTO> hotelImages = new ArrayList<>();
    private List<HotelOptionDTO> options =new ArrayList<>();
    private String remarks;
}
