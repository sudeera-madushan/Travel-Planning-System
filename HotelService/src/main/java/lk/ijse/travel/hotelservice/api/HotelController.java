package lk.ijse.travel.hotelservice.api;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public Response<HotelDTO> getHotel(@RequestParam String id) {
        return hotelService.getHotel(id);
    }


    @RequestMapping("save")
    @PostMapping
    public Response<HotelDTO> save(
            @RequestPart HotelDTO hotel,
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
        Response<HotelDTO> saved = hotelService.saveHotel(hotel);

        return saved;
    }

    @GetMapping
    public Response<List<HotelDTO>> getAllHotel() {
        return hotelService.getAllHotels();
    }

    @DeleteMapping
    public Response<String> deleteHotel(@RequestParam String id ){
        return hotelService.deleteHotelById(id);
    }

    @PutMapping
    public Response<HotelDTO> updateHotel(
            @RequestPart HotelDTO hotel,
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
        Response<HotelDTO> saved = hotelService.updateHotel(hotel);


        return saved;
    }

}
