package lk.ijse.travel.maintravelservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "area")
public class Area extends SuperEntity{
    private String name;
    private String description;
    @Column(name = "area_location" , columnDefinition = "TEXT")
    private String areaLocation;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
}
