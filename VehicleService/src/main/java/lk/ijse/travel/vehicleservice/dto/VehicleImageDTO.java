package lk.ijse.travel.vehicleservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lk.ijse.travel.vehicleservice.entity.SuperEntity;
import lk.ijse.travel.vehicleservice.entity.Vehicle;
import lombok.*;

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
public class VehicleImageDTO{
    private String id;
    private byte[] front_view,rear_view,side_view,front_interior,rear_interior;
    private VehicleDTO vehicle;
}
