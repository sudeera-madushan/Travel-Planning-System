package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.PackageCategoryService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.dto.PackageCategoryDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lk.ijse.travel.maintravelservice.persistence.PackageCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class PackageCategoryServiceImpl implements PackageCategoryService {
    @Autowired
    private Converter converter;
    @Autowired
    private PackageCategoryRepo packageCategoryRepo;
    @Override
    public Response<PackageCategoryDTO> savePackageCategory(PackageCategoryDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Package Category save successfully",
                converter.getPackageCategoryDTO(
                    packageCategoryRepo.save(
                            converter.getPackageCategoryEntity(dto)
                    )
                )
        );
    }

    @Override
    public Response<PackageCategoryDTO> getPackageCategory(String id) {
        return new Response<>(HttpStatus.OK,"Package Category get successfully",
                converter.getPackageCategoryDTO(
                        packageCategoryRepo.findById(id).get()
                ));
    }
}
