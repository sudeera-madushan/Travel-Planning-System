package lk.ijse.travel.maintravelservice.dto;

import jakarta.persistence.Entity;
import lk.ijse.travel.maintravelservice.entity.SuperEntity;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AreaImageDTO{
    private String id, areaId;
    private byte[] image;

    public AreaImageDTO(String areaId, byte[] image) {
        this.areaId = areaId;
        this.image = image;
    }
}
