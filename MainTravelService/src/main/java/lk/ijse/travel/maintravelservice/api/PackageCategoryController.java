package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.PackageCategoryService;
import lk.ijse.travel.maintravelservice.dto.PackageCategoryDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("api/v1/package")
public class PackageCategoryController {
    @Autowired
    private PackageCategoryService packageCategoryService;

    @PostMapping
    public Response<PackageCategoryDTO> savePackageCategory(
            @RequestPart String category,
            @RequestPart String hotelCategory,
            @RequestPart String vehicleCategory,
            @RequestPart byte[] image){
        return packageCategoryService.savePackageCategory(
                new PackageCategoryDTO(
                        category,
                        hotelCategory,
                        vehicleCategory,
                        image));
    }

    @GetMapping
    public Response<PackageCategoryDTO> getPackageCategory(@RequestParam String id){
        return packageCategoryService.getPackageCategory(id);
    }
    @RequestMapping("find")
    @GetMapping
    public Response<PackageCategoryDTO> getPackageCategoryByName(@RequestParam String name){
        return packageCategoryService.getPackageCategoryByName(name);
    }

}
