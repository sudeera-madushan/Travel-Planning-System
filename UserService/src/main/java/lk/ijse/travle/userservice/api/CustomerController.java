package lk.ijse.travle.userservice.api;

import lk.ijse.travle.userservice.bo.CustomerService;
import lk.ijse.travle.userservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

//    @RolesAllowed("ROLE_USER")
    @RequestMapping("get")
    @GetMapping
    public Response<CustomerDTO> getCustomer(@RequestParam String id) {
        System.out.println(id);
        return customerService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CustomerDTO> save(
            @RequestPart CustomerDTO customer,
//            @RequestPart String full_name,
//            @RequestPart String username,
//            @RequestPart String password,
//            @RequestPart String age,
//            @RequestPart String gender,
//            @RequestPart String email,
//            @RequestPart String contact_no,
//            @RequestPart String address,
//            @RequestPart String nic_or_passport_no,
            @RequestPart MultipartFile nic_or_passport_image_front,
            @RequestPart MultipartFile nic_or_passport_image_back
//                @RequestPart String remarks
    ) {
//        WebClient webClient = WebClient.create("http://localhost:8091/travel/user/save");
//
//        Mono<CustomerDTO> savedUser=webClient.post()
//                .body(BodyInserters.fromValue(new UserDTO("",username,password,"USER")))
//                .retrieve()
//                .bodyToMono(CustomerDTO.class);
//        CustomerDTO dto = savedUser.block();
//        CustomerDTO dto = new CustomerDTO();
//        dto.setUser(new UserDTO(0,username,password, Type.ROLE_USER));
//        dto.setFullName(full_name);
//        dto.setAge(Integer.parseInt(age));
//        dto.setEmail(email);
//        dto.setAddress(address);
//        dto.setGender(gender);
//        dto.setContactNo(contact_no);
//        dto.setNicOrPassportNo(nic_or_passport_no);
//        try {
//            dto.setNicOrPassportImageFront((nic_or_passport_image_front.getBytes()));
//            dto.setNicOrPassportImageBack((nic_or_passport_image_back.getBytes()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        dto.setRemarks(remarks);
//        return customerService.save(dto);
        return new Response<>(HttpStatus.OK,"",customer);
    }

    @DeleteMapping("delete")
    public String test(@RequestPart Test test,
                       @RequestPart byte[] image){
        return test.getName();
    }

}
