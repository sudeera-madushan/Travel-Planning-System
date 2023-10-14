package lk.ijse.travel.guideservice.api;

import lk.ijse.travel.guideservice.bo.GuideService;
import lk.ijse.travel.guideservice.dto.GuideDTO;
import lk.ijse.travel.guideservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("guide")
public class GuideController {
    @Autowired
    private GuideService guideService;

    //    @RolesAllowed("ROLE_USER")
    @RequestMapping("get")
    @GetMapping
    public Response<GuideDTO> getUserDetails(@RequestParam String id) {
        return guideService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<GuideDTO> save(
            @RequestPart String name,
            @RequestPart String address,
            @RequestPart String gender,
            @RequestPart String contact,
            @RequestPart String age,
            @RequestPart String experience,
            @RequestPart MultipartFile image,
            @RequestPart MultipartFile nic_image_front,
            @RequestPart MultipartFile nic_image_back,
            @RequestPart MultipartFile guide_id_image_front,
            @RequestPart MultipartFile guide_id_image_back,
            @RequestPart String man_day_value,
            @RequestPart String remarks) {
        GuideDTO dto = new GuideDTO();
        dto.setName(name);
        dto.setAddress(address);
        dto.setGender(gender);
        dto.setContact(contact);
        dto.setAge(Integer.parseInt(age));
        dto.setExperience(Integer.parseInt(experience));
        dto.setMan_day_value(new BigDecimal(man_day_value));
        dto.setRemarks(remarks);
        try {
            dto.setImage(image.getBytes());
            dto.setNic_image_front((nic_image_front.getBytes()));
            dto.setNic_image_back((nic_image_back.getBytes()));
            dto.setGuide_id_image_front((guide_id_image_front.getBytes()));
            dto.setGuide_id_image_back((guide_id_image_back.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return guideService.save(dto);
    }
}
