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
public class VehicleImageDTO{
    private String id;
    @NonNull
    private byte[] frontView, rearView, sideView, frontInterior, rearInterior;
    private VehicleDTO vehicle;
}
