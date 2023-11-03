package lk.ijse.travel.vehicleservice.api;

import jakarta.validation.Valid;
import lk.ijse.travel.vehicleservice.bo.VehicleService;
import lk.ijse.travel.vehicleservice.dto.Response;
import lk.ijse.travel.vehicleservice.dto.VehicleDTO;
import lk.ijse.travel.vehicleservice.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    public Response<VehicleDTO> getVehicle(@RequestParam String id) {
        return vehicleService.get(id);
    }
    @RequestMapping("getBy")
    @GetMapping
    public Response<VehicleDTO> getVehicleByID(@RequestParam String id) {
        Response<VehicleDTO> response = vehicleService.get(id);
        response.getObject().setVehicleImage(null);
        response.getObject().setDriverLicenseImageFront(null);
        response.getObject().setDriverLicenseImageBack(null);
        return response;
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping
    public Response<VehicleDTO> save(
            @Valid @RequestPart VehicleDTO vehicle,
            @RequestPart MultipartFile driver_license_image_front,
            @RequestPart MultipartFile driver_license_image_back,
            @RequestPart MultipartFile front_view,
            @RequestPart MultipartFile rear_view,
            @RequestPart MultipartFile side_view,
            @RequestPart MultipartFile front_interior,
            @RequestPart MultipartFile rear_interior
            ) {
        vehicle.setPackageCategoryId(getPackageCategoryByVehicleCategory(vehicle.getCategory()));
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
    @RequestMapping("cate&seat")
    @GetMapping
    public Response<List<VehicleDTO>> getAllVehicleByPackageCategoryAndSeat(@RequestParam String id,@RequestParam int seat){
        return vehicleService.getAllByPackageCategoryAndSeat(id,seat);
    }


    private String getPackageCategoryByVehicleCategory(String category){
            WebClient webClient = WebClient.create("http://localhost:8095/travel/api/v1/package/getByCategory?category=" + category);
            Mono<Response<String>> orderMono = webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<Response<String>>() {});
            return orderMono.block().getObject();
    }
}
