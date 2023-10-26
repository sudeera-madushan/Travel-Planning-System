package lk.ijse.travel.hotelservice.api;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.RoomTypeService;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.HotelOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/option")
public class OptionController {
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private HotelService hotelService;
    @PostMapping("save")
    public Response<HotelOptionDTO> saveRoomType(
            @RequestPart HotelOptionDTO option){
        return roomTypeService.saveRoomType(option);
    }
    @GetMapping
    public Response<HotelOptionDTO> getRoomType(@RequestParam String id){
        return roomTypeService.getRoomType(id);
    }
}
