package lk.ijse.travel.guideservice.api;

import lk.ijse.travel.guideservice.bo.GuideService;
import lk.ijse.travel.guideservice.dto.GuideDTO;
import lk.ijse.travel.guideservice.dto.Response;
import lk.ijse.travel.guideservice.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RestController
@RequestMapping("guide")
public class GuideController {
    @Autowired
    private GuideService guideService;

    @RequestMapping("get")
    @GetMapping
    public Response<GuideDTO> getUserDetails(@RequestParam String id) {
        return guideService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping
    public Response<GuideDTO> save(
            @RequestPart GuideDTO guide,
            @RequestPart MultipartFile image,
            @RequestPart MultipartFile nic_image_front,
            @RequestPart MultipartFile nic_image_back,
            @RequestPart MultipartFile guide_id_image_front,
            @RequestPart MultipartFile guide_id_image_back
    ) {
        try {
            guide.setImage(image.getBytes());
            guide.setNicImageFront((nic_image_front.getBytes()));
            guide.setNicImageBack((nic_image_back.getBytes()));
            guide.setGuideIdImageFront((guide_id_image_front.getBytes()));
            guide.setGuideIdImageBack((guide_id_image_back.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return guideService.save(guide);
    }
    @RequestMapping("test")
    @ResponseBody
    @PostMapping
    public Response<GuideDTO> save(
            @RequestPart Test test
    ){
        System.out.println(test);
        return null;
    }

    @RequestMapping("getAll")
    @ResponseBody
    @PostMapping
    public Response<List<GuideDTO>> getAllGuides(){
        return guideService.getAll();
    }

    @ResponseBody
    @DeleteMapping
    public Response<String> deleteGuide(@RequestParam String id){
        return guideService.delete(id);
    }

    @RequestMapping
    @ResponseBody
    @PatchMapping
    public Response<GuideDTO> updateGuide(
            @RequestPart GuideDTO guide,
            @RequestPart MultipartFile image,
            @RequestPart MultipartFile nic_image_front,
            @RequestPart MultipartFile nic_image_back,
            @RequestPart MultipartFile guide_id_image_front,
            @RequestPart MultipartFile guide_id_image_back
    ) {
        try {
            guide.setImage(image.getBytes());
            guide.setNicImageFront((nic_image_front.getBytes()));
            guide.setNicImageBack((nic_image_back.getBytes()));
            guide.setGuideIdImageFront((guide_id_image_front.getBytes()));
            guide.setGuideIdImageBack((guide_id_image_back.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return guideService.update(guide);
    }
}
