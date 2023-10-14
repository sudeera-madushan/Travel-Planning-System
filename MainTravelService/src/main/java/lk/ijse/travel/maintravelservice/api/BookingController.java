package lk.ijse.travel.maintravelservice.api;

import lk.ijse.travel.maintravelservice.bo.BookingService;
import lk.ijse.travel.maintravelservice.dto.BookingDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */

@RestController
@RequestMapping("booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Response<BookingDTO> saveBooking(@RequestBody BookingDTO dto){
        return bookingService.saveBooking(dto);
    }
    @GetMapping
    public Response<BookingDTO> getBooking(@RequestParam String id){
        return bookingService.getBooking(id);
    }
}
