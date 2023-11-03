package lk.ijse.travel.guideservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

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
public class GuideDTO {
    private String id;
    @NotNull
    private String name,address,gender,contact;
    @NotNull
    @Min(20)
    private int age;
    @NotNull
    private int experience;
    @NotNull
    private byte[] image, nicImageFront, nicImageBack, guideIdImageFront, guideIdImageBack;
    @NotNull
    @Min(1000)
    private BigDecimal manDayValue;
    private String remarks;
}
