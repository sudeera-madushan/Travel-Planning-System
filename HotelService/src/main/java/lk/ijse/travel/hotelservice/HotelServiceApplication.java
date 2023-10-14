package lk.ijse.travel.hotelservice;

import lk.ijse.travel.hotelservice.bo.util.Base64ToByteConverter;
import lk.ijse.travel.hotelservice.bo.util.ImageConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new ImageConverter());
        modelMapper.addConverter(new Base64ToByteConverter());
        return modelMapper;
    }

}
