package lk.ijse.travel.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "vehicle_image")
public class VehicleImage extends SuperEntity{
    @Column(name = "front_view", columnDefinition = "LONGTEXT")
    private String frontView;
    @Column(name = "rear_view", columnDefinition = "LONGTEXT")
    private String rearView;
    @Column(name = "side_view", columnDefinition = "LONGTEXT")
    private String sideView;
    @Column(name = "front_interior", columnDefinition = "LONGTEXT")
    private String frontInterior;
    @Column(name = "rear_interior", columnDefinition = "LONGTEXT")
    private String rearInterior;
//    @OneToOne
//    @JoinColumn(name = "vehicle_id")
//    private Vehicle vehicle;

    @OneToOne(mappedBy = "vehicleImage")
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
