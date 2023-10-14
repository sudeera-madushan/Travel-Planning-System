package lk.ijse.travel.maintravelservice.dto;

import lk.ijse.travel.maintravelservice.entity.SuperEntity;
import lombok.*;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO extends SuperEntity {
    private String id,areaLocation, description;
    private byte[] video;
    private List<byte[]> images;

    public AreaDTO(String areaLocation, String description, byte[] video, List<byte[]> images) {
        this.areaLocation = areaLocation;
        this.description = description;
        this.video = video;
        this.images = images;
    }
}
