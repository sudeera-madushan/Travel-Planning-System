package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.HotelListService;
import lk.ijse.travel.maintravelservice.dto.HotelListDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("hotelList")
public class HotelListController {
    @Autowired
    private HotelListService hotelListService;

    @PostMapping
    public Response<List<HotelListDTO>> saveHotelList(
            @RequestBody HotelListDTO[] list
    ){
        return hotelListService.saveHotelList(Arrays.stream(list).toList());
    }
    @GetMapping("all")
    public Response<List<HotelListDTO>> getHotelListByBooking(@RequestParam String id){
        return hotelListService.getHotelListByBookingId(id);
    }
}
