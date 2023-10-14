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
@RestController
@RequestMapping("hotel")
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<HotelDTO> save(
            @RequestPart String name,
            @RequestPart String category,
            @RequestPart String location,
            @RequestPart String email,
            @RequestPart String mapLocation,
            @RequestPart String contactNoOne,
            @RequestPart String contactNoTwo,
            @RequestPart String petIsAllowed,
            @RequestPart String hotelFee,
            @RequestPart String cancellationCriteriaIsFree,
            @RequestPart String cancellationFee,
            @RequestPart String packageCategoryId,
            @RequestPart List<MultipartFile> images) {
        HotelDTO dto = new HotelDTO();
        dto.setName(name);
        dto.setCategory(category);
        dto.setLocation(location);
        dto.setEmail(email);
        dto.setMapLocation(mapLocation);
        dto.setContactNoOne(contactNoOne);
        dto.setPackageCategoryId(packageCategoryId);
        dto.setContactNoTwo(contactNoTwo);
        dto.setPetIsAllowed(petIsAllowed.equalsIgnoreCase("true"));
        dto.setHotelFee(new BigDecimal(hotelFee));
        dto.setCancellationCriteriaIsFree(cancellationCriteriaIsFree.equalsIgnoreCase("true"));
        dto.setCancellationFee(new BigDecimal(cancellationFee));
//        try {
//            ArrayList<byte[]> list = new ArrayList<>();
//            for (MultipartFile image : images) {
//                list.add(image.getBytes());
//        List<byte[]> list = images.stream().map(multipartFile -> {
//            try {
//                return multipartFile.getBytes();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }).toList();
//        dto.setImages(list);

        images.forEach(multipartFile -> {
            try {
                dto.getImages().add(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return hotelService.saveHotel(dto);
    }
}
