package lk.ijse.travel.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private byte[] front_view,rear_view,side_view,front_interior,rear_interior;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
