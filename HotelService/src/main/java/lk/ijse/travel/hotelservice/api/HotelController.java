package lk.ijse.travel.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<HotelDTO> getHotel(@RequestParam String id) {
        return hotelService.getHotel(id);
    }
    @RequestMapping("getBy")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<HotelDTO> getHotelBy(@RequestParam String id) {
        Response<HotelDTO> hotel = hotelService.getHotel(id);
        hotel.getObject().setHotelImages(null);
        return hotel;
    }


    @RequestMapping("save")
    @PostMapping
    public Response<HotelDTO> save(
            @Valid @RequestPart HotelDTO hotel,
            @RequestPart List<MultipartFile> images) {
        ArrayList<HotelImageDTO> imageDTOArrayList = new ArrayList<>();
        try {
            for (MultipartFile image : images) {
                imageDTOArrayList.add(new HotelImageDTO(image.getBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hotel.setHotelImages(imageDTOArrayList);
        return hotelService.saveHotel(hotel);
    }

    @GetMapping
    public Response<List<HotelDTO>> getAllHotel() {
        return hotelService.getAllHotels();
    }

    @RequestMapping("category")
    @GetMapping
    public Response<List<HotelDTO>> getAllHotelsByPackageCategory(@RequestParam String id) {
        return hotelService.getAllHotelsByPackageCategory(id);
    }

    @DeleteMapping
    public Response<String> deleteHotel(@RequestParam String id ){
        return hotelService.deleteHotelById(id);
    }

    @PutMapping
    public Response<HotelDTO> updateHotel(
            @Valid @RequestPart HotelDTO hotel,
            @RequestPart List<MultipartFile> images
    ) {
        ArrayList<HotelImageDTO> imageDTOArrayList = new ArrayList<>();
        try {
            for (MultipartFile image : images) {
                imageDTOArrayList.add(new HotelImageDTO(image.getBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hotel.setHotelImages(imageDTOArrayList);
        return  hotelService.updateHotel(hotel);
    }
    @RequestMapping("nears")
    @GetMapping
    Response<List<HotelDTO>> findNearestPlaces(@RequestParam String id){
        return hotelService.findNearestHotels(id);
    }
}
