package lk.ijse.travel.hotelservice.api;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @RequestMapping("get")
    @GetMapping
    public Response<HotelDTO> getUserDetails(@RequestParam String id) {
        return hotelService.getHotel(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping
    public Response<HotelDTO> save(
            @RequestPart HotelDTO hotel
//            @RequestPart MultipartFile images
            ) {
//        hotel.setImages(new ArrayList<>());
//        for (MultipartFile image : images) {
//            try {
//                hotel.getImages().add(image.getBytes());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return hotelService.saveHotel(hotel);
    }
}
