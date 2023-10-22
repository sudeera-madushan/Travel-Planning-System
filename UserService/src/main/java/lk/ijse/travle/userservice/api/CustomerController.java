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
@RequestMapping("api/v1/customer")
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
    @PostMapping()
    public Response<CustomerDTO> save(
            @RequestPart CustomerDTO customer,
            @RequestPart byte[] nic_or_passport_image_front,
            @RequestPart byte[] nic_or_passport_image_back
    ) {
        customer.setNicOrPassportImageFront(nic_or_passport_image_front);
        customer.setNicOrPassportImageBack(nic_or_passport_image_back);
        return customerService.save(customer);
    }

    @DeleteMapping("delete")
    public String test(){
        return "Successfully";
    }

}
