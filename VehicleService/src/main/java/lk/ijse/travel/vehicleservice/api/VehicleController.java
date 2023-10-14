package lk.ijse.travel.vehicleservice.api;

import lk.ijse.travel.vehicleservice.bo.VehicleService;
import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @RequestMapping("get")
    @GetMapping
    public Response<VehicleDTO> getUserDetails(@RequestParam String id) {
        return vehicleService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<VehicleDTO> save(
            @RequestPart String brand,
            @RequestPart String category,
            @RequestPart String fuel_type,
            @RequestPart String vehicle_type,
            @RequestPart String transmission_type,
            @RequestPart String driver_name,
            @RequestPart String driver_contact,
            @RequestPart String is_hybrid,
            @RequestPart String fuel_usage,
            @RequestPart String seat_capacity,
            @RequestPart MultipartFile driver_license_image_front,
            @RequestPart MultipartFile driver_license_image_back,
            @RequestPart MultipartFile front_view,
            @RequestPart MultipartFile rear_view,
            @RequestPart MultipartFile front_interior,
            @RequestPart MultipartFile rear_interior,
            @RequestPart String remarks,
            @RequestPart String packageCategoryId
            ) {
        VehicleDTO vehicle = new VehicleDTO();
        VehicleImageDTO vehicleImage = new VehicleImageDTO();
        try {
            vehicleImage.setFront_view(front_view.getBytes());
            vehicleImage.setRear_view(rear_view.getBytes());
            vehicleImage.setRear_interior(rear_interior.getBytes());
            vehicleImage.setFront_interior(front_interior.getBytes());
            vehicle.setDriver_license_image_front(driver_license_image_front.getBytes());
            vehicle.setDriver_license_image_back(driver_license_image_back.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vehicle.setRemarks(remarks);
        vehicle.setBrand(brand);
        vehicle.setPackageCategoryId(packageCategoryId);
        vehicle.setCategory(category);
        vehicle.setFuel_type(fuel_type);
        vehicle.setVehicle_type(vehicle_type);
        vehicle.setTransmission_type(transmission_type);
        vehicle.setDriver_name(driver_name);
        vehicle.setDriver_contact(driver_contact);
        vehicle.set_hybrid(is_hybrid.equalsIgnoreCase("true"));
        vehicle.setFuel_usage(Integer.parseInt(fuel_usage));
        vehicle.setSeat_capacity(Integer.parseInt(seat_capacity));
        return vehicleService.save(vehicle);
    }
}
