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
@Table(name = "package_category")
public class PackageCategory extends SuperEntity{
    @Column(unique = true)
    private String category;
    @Column(name = "hotel_category")
    private String hotelCategory;
    @Column(name = "vehicle_category")
    private String vehicleCategory;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
}
