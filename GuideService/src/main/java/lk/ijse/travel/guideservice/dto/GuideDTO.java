package lk.ijse.travel.guideservice.dto;

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
    String id;
    String name,address,gender,contact;
    int age,experience;
    byte[] image, nicImageFront, nicImageBack, guideIdImageFront, guideIdImageBack;
    BigDecimal manDayValue;
    String remarks;
}
