package lk.ijse.travel.vehicleservice.api;

import lk.ijse.travel.vehicleservice.bo.VehicleService;
import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @RequestMapping("get")
    @GetMapping
    public Response<VehicleDTO> getVehicleByID(@RequestParam String id) {
        return vehicleService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping
    public Response<VehicleDTO> save(
            @RequestPart VehicleDTO vehicle,
            @RequestPart MultipartFile driver_license_image_front,
            @RequestPart MultipartFile driver_license_image_back,
            @RequestPart MultipartFile front_view,
            @RequestPart MultipartFile rear_view,
            @RequestPart MultipartFile side_view,
            @RequestPart MultipartFile front_interior,
            @RequestPart MultipartFile rear_interior
            ) {
        VehicleImageDTO vehicleImage = new VehicleImageDTO();
        try {
            vehicleImage.setFrontView(front_view.getBytes());
            vehicleImage.setRearView(rear_view.getBytes());
            vehicleImage.setSideView(side_view.getBytes());
            vehicleImage.setRearInterior(rear_interior.getBytes());
            vehicleImage.setFrontInterior(front_interior.getBytes());
            vehicle.setDriverLicenseImageFront(driver_license_image_front.getBytes());
            vehicle.setDriverLicenseImageBack(driver_license_image_back.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vehicle.setVehicleImage(vehicleImage);
        return vehicleService.save(vehicle);
    }
    @ResponseBody
    @GetMapping
    public Response<List<VehicleDTO>> getAllVehicle(){
        return vehicleService.getAll();
    }

    @DeleteMapping
    public Response<String> deleteVehicle(@RequestParam String id ,
                                          @RequestParam String imageId){
        return vehicleService.deleteVehicle(id,imageId);
    }

    @RequestMapping("category")
    @GetMapping
    public Response<List<VehicleDTO>> getAllVehicleByPackageCategory(@RequestParam String id){
        return vehicleService.getAllByPackageCategory(id);
    }
}
