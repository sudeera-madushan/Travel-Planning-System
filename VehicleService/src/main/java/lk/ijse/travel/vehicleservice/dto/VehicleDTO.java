package lk.ijse.travel.vehicleservice.dto;

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
    private String id,brand,category, fuelType, vehicleType, transmissionType, driverName, driverContact,remarks;
    private boolean isHybrid;
    private int fuelUsage, seatCapacity;
    private byte[] driverLicenseImageFront, driverLicenseImageBack;
    private VehicleImageDTO vehicleImage;
    private String packageCategoryId;

}