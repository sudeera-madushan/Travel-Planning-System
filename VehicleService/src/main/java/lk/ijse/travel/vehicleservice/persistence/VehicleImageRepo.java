package lk.ijse.travel.vehicleservice.persistence;

import lk.ijse.travel.vehicleservice.entity.Vehicle;
import lk.ijse.travel.vehicleservice.entity.VehicleImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/23/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface VehicleImageRepo extends CrudRepository<VehicleImage,String> {
}
