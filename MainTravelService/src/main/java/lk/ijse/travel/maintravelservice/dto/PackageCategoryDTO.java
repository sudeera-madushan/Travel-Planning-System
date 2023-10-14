package lk.ijse.travel.maintravelservice.dto;

import jakarta.persistence.Column;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PackageCategoryDTO {

    private String id,category,hotelCategory, vehicleCategory;
    private byte[] image;

    public PackageCategoryDTO(String category, String hotelCategory, String vehicleCategory, byte[] image) {
        this.category = category;
        this.hotelCategory = hotelCategory;
        this.vehicleCategory = vehicleCategory;
        this.image = image;
    }
}
