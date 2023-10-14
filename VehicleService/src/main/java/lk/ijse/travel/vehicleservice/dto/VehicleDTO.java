package lk.ijse.travel.vehicleservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lk.ijse.travel.vehicleservice.entity.SuperEntity;
import lk.ijse.travel.vehicleservice.entity.VehicleImage;
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
public class VehicleDTO {
    private String id,brand,category,fuel_type,vehicle_type,transmission_type,driver_name,driver_contact,remarks;
    private boolean is_hybrid;
    private int fuel_usage,seat_capacity;
    private byte[] driver_license_image_front,driver_license_image_back;
    private VehicleImageDTO vehicleImage;
    private String packageCategoryId;

}