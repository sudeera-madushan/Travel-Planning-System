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
    @Column(name = "area_location")
    private String areaLocation;
    @Column(columnDefinition = "LONGTEXT")
    private String video;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
}
