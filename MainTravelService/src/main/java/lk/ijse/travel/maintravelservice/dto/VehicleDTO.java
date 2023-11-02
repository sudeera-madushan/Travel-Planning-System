package lk.ijse.travel.maintravelservice.dto;

import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal feeForDay;
    private BigDecimal feeForKm;

    private String packageCategoryId;

}