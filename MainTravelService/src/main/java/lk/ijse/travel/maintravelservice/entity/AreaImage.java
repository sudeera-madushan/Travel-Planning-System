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
@Table(name = "area_image")
public class AreaImage extends SuperEntity{
    @Column(name = "area_id")
    private String areaId;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
}
