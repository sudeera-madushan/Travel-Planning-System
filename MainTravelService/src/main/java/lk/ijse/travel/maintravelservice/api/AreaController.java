package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.AreaService;
import lk.ijse.travel.maintravelservice.dto.AreaDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @PostMapping
    private Response<AreaDTO> saveArea(
            @RequestPart String areaLocation,
            @RequestPart String description,
            @RequestPart byte[] video,
            @RequestPart List<MultipartFile> images

    ){
        ArrayList<byte[]> list = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                list.add(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return areaService.saveArea(new AreaDTO(areaLocation,description,video,list));
    }

    @GetMapping
    private Response<AreaDTO> getArea(@RequestParam String id){
        return areaService.getArea(id);
    }
}
