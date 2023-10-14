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
@Table(name = "vehicle")
public class Vehicle extends SuperEntity{
    private String brand,category,fuel_type,vehicle_type,transmission_type,driver_name,driver_contact,remarks;
    private boolean is_hybrid;
    private int fuel_usage,seat_capacity;
    private byte[] driver_license_image_front,driver_license_image_back;
    @OneToOne
    @JoinColumn(name = "vehicle_image_id")
    private VehicleImage vehicleImage;
    private String packageCategoryId;

}
