package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.AreaImageService;
import lk.ijse.travel.maintravelservice.dto.AreaImageDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("api/v1/areaImage")
public class AreaImageController {
    @Autowired
    private AreaImageService areaImageService;
    @RequestMapping("all")
    @GetMapping
    public Response<List<AreaImageDTO>> getAllImages(@RequestParam String id){
        return areaImageService.getAllByAreaId(id);
    }
    @GetMapping()
    public Response<AreaImageDTO> getImages(@RequestParam String id){
        return areaImageService.getAreaImage(id);
    }
}
