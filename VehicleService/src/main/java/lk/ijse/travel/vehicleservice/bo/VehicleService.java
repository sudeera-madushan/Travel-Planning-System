package lk.ijse.travel.vehicleservice.bo;

import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface VehicleService {
    Response<VehicleDTO> save(VehicleDTO dto);
    Response<VehicleDTO> get(String id);
}
