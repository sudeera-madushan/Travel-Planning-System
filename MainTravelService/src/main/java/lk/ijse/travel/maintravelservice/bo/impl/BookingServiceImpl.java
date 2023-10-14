package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.BookingService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.dto.BookingDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lk.ijse.travel.maintravelservice.persistence.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private Converter converter;
    @Transactional
    @Override
    public Response<BookingDTO> saveBooking(BookingDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Booking save successfully",
                converter.getBookingDTO(
                        bookingRepo.save(
                                converter.getBookingEntity(dto)
                        )
                ));
    }

    @Override
    public Response<BookingDTO> getBooking(String id) {
        return new Response<>(HttpStatus.CREATED,"Booking save successfully",
                converter.getBookingDTO(
                        bookingRepo.findById(id).get()
                ));
    }
}
