package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.AreaService;
import lk.ijse.travel.maintravelservice.dto.AreaDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping
    private Response<AreaDTO> saveArea(
            @RequestPart String name,
            @RequestPart String description,
            @RequestPart String areaLocation,
            @RequestPart byte[] image,
            @RequestPart List<MultipartFile> images

    ){
        ArrayList<byte[]> list = new ArrayList<>();
        for (MultipartFile file : images) {
            try {
                list.add(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return areaService.saveArea(new AreaDTO(name,description,areaLocation,image,list));
    }

    @RequestMapping("get")
    @GetMapping
    private Response<AreaDTO> getArea(@RequestParam String id){
        return areaService.getArea(id);
    }

    @GetMapping
    Response<List<AreaDTO>> getAllArea(){
        return areaService.getAll();
    }
}
