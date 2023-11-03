package lk.ijse.travel.vehicleservice.dto;

import jakarta.validation.constraints.NotNull;
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

    private String id;
    @NotNull
    private String brand,category, fuelType, vehicleType, transmissionType, driverName, driverContact;
    private String remarks;
    @NotNull
    private boolean isHybrid;
    @NotNull
    private int fuelUsage, seatCapacity;
    @NotNull
    private byte[] driverLicenseImageFront, driverLicenseImageBack;
    private VehicleImageDTO vehicleImage;
    @NotNull
    private BigDecimal feeForDay;
    @NotNull
    private BigDecimal feeForKm;
    private String packageCategoryId;

}