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
    @RequestMapping("get")
    @GetMapping
    public Response<CustomerDTO> getCustomer(@RequestParam String id) {
        return customerService.get(id);
    }
    @RequestMapping("save")
    @ResponseBody
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<CustomerDTO> save(
            @RequestPart CustomerDTO customer,
            @RequestPart MultipartFile nic_or_passport_image_front,
            @RequestPart MultipartFile nic_or_passport_image_back
    ) {
        return new Response<>(HttpStatus.OK,"",customer);
    }

    @DeleteMapping("delete")
    public String test(@RequestPart Test test,
                       @RequestPart byte[] image){
        return test.getName();
    }

}
