package lk.ijse.travel.hotelservice.api;

import lk.ijse.travel.hotelservice.bo.HotelService;
import lk.ijse.travel.hotelservice.dto.HotelDTO;
import lk.ijse.travel.hotelservice.dto.HotelImageDTO;
import lk.ijse.travel.hotelservice.dto.Response;
import lk.ijse.travel.hotelservice.dto.RoomTypeDTO;
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
    @PostMapping
    public Response<HotelDTO> save(
            @RequestPart HotelDTO hotel,
            @RequestPart List<MultipartFile> images,
            @RequestPart List<MultipartFile> roomType
            ) {
        ArrayList<HotelImageDTO> imageDTOArrayList = new ArrayList<>();
            try {
                for (MultipartFile image : images) {
                    imageDTOArrayList.add(new HotelImageDTO(image.getBytes()));
                }
                for (int i = 0; i < roomType.size(); i++) {
                    hotel.getRoomTypes().get(i).setImageData(roomType.get(i).getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            hotel.setHotelImages(imageDTOArrayList);
        Response<HotelDTO> saved = hotelService.saveHotel(hotel);
//        saved.getObject().setRoomTypes(null);
        saved.getObject().setHotelImages(null);

        return saved;
    }
}
