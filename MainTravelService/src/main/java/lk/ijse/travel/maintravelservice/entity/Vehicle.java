package lk.ijse.travel.maintravelservice.entity;

import jakarta.persistence.Entity;
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
public class Vehicle {
    private String brand,category, fuelType, vehicleType, transmissionType, driverName, driverContact;
    private boolean isHybrid;
    private int fuelUsage, seatCapacity;
    private BigDecimal feeForDay;
    private BigDecimal feeForKm;

}