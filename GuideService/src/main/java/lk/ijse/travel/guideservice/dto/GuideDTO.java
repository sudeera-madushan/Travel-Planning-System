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
    String name,address,gender,contact;
    String id;
    int age,experience;
    byte[] image,nic_image_front,nic_image_back,guide_id_image_front,guide_id_image_back;
    BigDecimal man_day_value;
    String remarks;
}
