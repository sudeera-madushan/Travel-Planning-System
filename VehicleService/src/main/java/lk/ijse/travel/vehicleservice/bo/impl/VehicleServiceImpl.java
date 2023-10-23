package lk.ijse.travel.vehicleservice.bo.impl;

import lk.ijse.travel.vehicleservice.bo.VehicleService;
import lk.ijse.travel.vehicleservice.bo.util.Converter;
import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.entity.Vehicle;
import lk.ijse.travel.vehicleservice.entity.VehicleImage;
import lk.ijse.travel.vehicleservice.persistence.VehicleImageRepo;
import lk.ijse.travel.vehicleservice.persistence.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private Converter converter;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private VehicleImageRepo vehicleImageRepo;
    @Transactional
    @Override
    public Response<VehicleDTO> save(VehicleDTO dto) {
        VehicleImage vehicleImage= converter.getVehicleImageEntity(dto.getVehicleImage());
        VehicleImage image = vehicleImageRepo.save(vehicleImage);
        dto.setVehicleImage(converter.getVehicleImageDTO(image));
        Vehicle vehicle = vehicleRepo.save(converter.getVehicleEntity(dto));
//        vehicleImage.setVehicle(vehicle);
//        vehicle.setVehicleImage(image);
        return new Response<>(HttpStatus.CREATED,"Vehicle save Successfully",
                converter.getVehicleDTO(vehicle));
    }

    @Override
    public Response<VehicleDTO> get(String id) {
        return new Response<>(HttpStatus.OK,"Vehicle find Successfully",
                converter.getVehicleDTO(vehicleRepo.findById(id).get()));
    }
    @Override
    public Response<List<VehicleDTO>> getAll(){
        return new Response<>(HttpStatus.OK,"Get All Vehicle successfully",
                converter.getAllVehicleDTO(vehicleRepo.findAll()));
    }
}
