package lk.ijse.travel.guideservice.entity;

import jakarta.persistence.*;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guide")
public class Guide extends SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    String name,address,gender,contact;
    int age,experience;
    byte[] image,nic_image_front,nic_image_back,guide_id_image_front,guide_id_image_back;
    BigDecimal man_day_value;
    @Column(columnDefinition = "Text")
    String remarks;
}
