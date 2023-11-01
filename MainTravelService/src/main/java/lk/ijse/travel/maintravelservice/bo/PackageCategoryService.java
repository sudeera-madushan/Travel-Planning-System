package lk.ijse.travel.maintravelservice.bo;

import lk.ijse.travel.maintravelservice.dto.PackageCategoryDTO;
import lk.ijse.travel.maintravelservice.dto.Response;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface PackageCategoryService {
    Response<PackageCategoryDTO> savePackageCategory(PackageCategoryDTO dto);
    Response<PackageCategoryDTO> getPackageCategory(String id);

    Response<PackageCategoryDTO> getPackageCategoryByName(String name);
}
