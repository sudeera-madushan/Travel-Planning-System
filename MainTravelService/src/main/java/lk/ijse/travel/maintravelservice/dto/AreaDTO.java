package lk.ijse.travel.maintravelservice.dto;

import lk.ijse.travel.maintravelservice.entity.SuperEntity;
import lombok.*;

import java.util.ArrayList;
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
    private String id,name, description,areaLocation;
    private byte[] image;
    private List<byte[]> images=new ArrayList<>();

    public AreaDTO(String name, String description, String areaLocation, byte[] image, List<byte[]> images) {
        this.name = name;
        this.description = description;
        this.areaLocation = areaLocation;
        this.image = image;
        this.images = images;
    }
}
