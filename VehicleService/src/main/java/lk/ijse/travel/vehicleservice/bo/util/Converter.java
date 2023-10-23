package lk.ijse.travel.vehicleservice.bo.util;

import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.dto.VehicleImageDTO;
import lk.ijse.travel.vehicleservice.entity.Vehicle;
import lk.ijse.travel.vehicleservice.entity.VehicleImage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;
    public Vehicle getVehicleEntity(VehicleDTO dto){
        return modelMapper.map(dto, Vehicle.class);
    }
    public VehicleDTO getVehicleDTO(Vehicle entity){
        return modelMapper.map(entity, VehicleDTO.class);
    }
    public VehicleImageDTO getVehicleImageDTO(VehicleImage entity){
        return modelMapper.map(entity, VehicleImageDTO.class);
    }
    public VehicleImage getVehicleImageEntity(VehicleImageDTO entity){
        return modelMapper.map(entity, VehicleImage.class);
    }

    public List<VehicleDTO> getAllVehicleDTO(List<Vehicle> list) {
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : list) {
            VehicleImageDTO imageDTO = getVehicleImageDTO(vehicle.getVehicleImage());
            vehicle.setVehicleImage(null);
            imageDTO.setVehicle(null);
            VehicleDTO vehicleDTO = getVehicleDTO(vehicle);
            vehicleDTO.setVehicleImage(imageDTO);
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }
}
