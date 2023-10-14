package lk.ijse.travel.hotelservice.api;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.bo.RoomTypeService;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.RoomTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private HotelService hotelService;
    @PostMapping("save")
    public Response<RoomTypeDTO> saveRoomType(
            @RequestPart String type,
            @RequestPart String hotelId,
            @RequestPart byte[] imageData){
        return roomTypeService.saveRoomType(new RoomTypeDTO(type
                ,imageData
                ,hotelService.getHotel(hotelId).getObject()
        ));
    }
    @GetMapping
    public Response<RoomTypeDTO> getRoomType(@RequestParam String id){
        return roomTypeService.getRoomType(id);
    }
}
