package lk.ijse.travel.vehicleservice.entity;

import jakarta.persistence.*;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle extends SuperEntity{
    private String brand,category;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Column(name = "transmission_type")
    private String transmissionType;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "driver_contact")
    private String driverContact;
    @Column(columnDefinition = "LONGTEXT")
    @Lob
    private String remarks;
    @Column(name = "is_hybrid")
    private boolean isHybrid;
    @Column(name = "fuel_usage")
    private int fuelUsage;
    @Column(name = "seat_capacity")
    private int seatCapacity;
    @Column(name = "driver_license_image_front", columnDefinition = "LONGTEXT")
    private String driverLicenseImageFront;
    @Column(name = "driver_license_image_back", columnDefinition = "LONGTEXT")
    private String driverLicenseImageBack;
//    @OneToOne(mappedBy = "vehicle")
//    @JoinColumn(name = "vehicle_image_id")
//    private VehicleImage vehicleImage;

    private BigDecimal feeForDay;
    private BigDecimal feeForKm;

    @OneToOne
    @JoinColumn(name = "vehicle_image_id")
    private VehicleImage vehicleImage;


    private String packageCategoryId;

}
