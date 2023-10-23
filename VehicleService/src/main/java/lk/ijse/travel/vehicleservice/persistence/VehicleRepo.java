package lk.ijse.travel.vehicleservice.persistence;

import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface VehicleRepo extends CrudRepository<Vehicle,String> {
    List<Vehicle> findAll();
}
