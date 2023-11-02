package lk.ijse.travel.maintravelservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/2/2023
 * @Project : Next Travel Pvt. Ltd
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseHotel {
    private String id,name,category,location,email,mapLocation,contactNoOne, contactNoTwo;
    private boolean petIsAllowed;
    private boolean cancellationCriteriaIsFree;
    private BigDecimal cancellationFee;
    private String packageCategoryId;
    private String remarks;
    private String type;
    private String area;
}
