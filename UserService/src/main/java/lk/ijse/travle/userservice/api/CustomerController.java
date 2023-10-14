package lk.ijse.travle.userservice.api;

import jakarta.annotation.security.RolesAllowed;
import lk.ijse.travle.userservice.bo.CustomerService;
import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("user")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

//    @RolesAllowed("ROLE_USER")
    @RequestMapping("get")
    @GetMapping
    public Response<CustomerDTO> getUserDetails(@RequestParam String id) {
        System.out.println(id);
        return customerService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CustomerDTO> save(
            @RequestPart String full_name,
            @RequestPart String username,
            @RequestPart String password,
            @RequestPart String age,
            @RequestPart String gender,
            @RequestPart String email,
            @RequestPart String contact_no,
            @RequestPart String address,
            @RequestPart String nic_or_passport_no,
            @RequestPart MultipartFile nic_or_passport_image_front,
            @RequestPart MultipartFile nic_or_passport_image_back,
                @RequestPart String remarks) {
//        WebClient webClient = WebClient.create("http://localhost:8091/travel/user/save");
//
//        Mono<CustomerDTO> savedUser=webClient.post()
//                .body(BodyInserters.fromValue(new UserDTO("",username,password,"USER")))
//                .retrieve()
//                .bodyToMono(CustomerDTO.class);
//        CustomerDTO dto = savedUser.block();
        CustomerDTO dto = new CustomerDTO();
        dto.setUser(new UserDTO(0,username,password, Type.ROLE_USER));
        dto.setFull_name(full_name);
        dto.setAge(Integer.parseInt(age));
        dto.setEmail(email);
        dto.setAddress(address);
        dto.setGender(gender);
        dto.setContact_no(contact_no);
        dto.setNic_or_passport_no(nic_or_passport_no);
        try {
            dto.setNic_or_passport_image_front((nic_or_passport_image_front.getBytes()));
            dto.setNic_or_passport_image_back((nic_or_passport_image_back.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dto.setRemarks(remarks);
        return customerService.save(dto);
    }
}
