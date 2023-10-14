package lk.ijse.travel.vehicleservice.bo.impl;

import lk.ijse.travel.vehicleservice.bo.VehicleService;
import lk.ijse.travel.vehicleservice.bo.util.Converter;
import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.persistence.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public Response<VehicleDTO> save(VehicleDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Vehicle save Successfully",
                converter.getVehicleDTO(vehicleRepo.save(converter.getVehicleEntity(dto))));
    }

    @Override
    public Response<VehicleDTO> get(String id) {
        return new Response<>(HttpStatus.OK,"Vehicle find Successfully",
                converter.getVehicleDTO(vehicleRepo.findById(id).get()));
    }
}
