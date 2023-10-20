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
    private String name,address,gender,contact;
    private int age,experience;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @Column(columnDefinition = "LONGTEXT" ,name = "nic_image_front")
    private String nicImageFront;
    @Column(columnDefinition = "LONGTEXT",name = "nic_image_back")
    private String nicImageBack;
    @Column(columnDefinition = "LONGTEXT",name = "guide_id_image_front")
    private String guideIdImageFront;
    @Column(columnDefinition = "LONGTEXT",name = "guide_id_image_back")
    private String guideIdImageBack;
    @Column(name = "man_day_value")
    private BigDecimal manDayValue;
    private String remarks;
}
