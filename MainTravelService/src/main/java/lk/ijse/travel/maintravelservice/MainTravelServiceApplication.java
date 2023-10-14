package lk.ijse.travel.maintravelservice;

import lk.ijse.travel.maintravelservice.bo.util.Base64ToByteConverter;
import lk.ijse.travel.maintravelservice.bo.util.ImageConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainTravelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainTravelServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Base64ToByteConverter());
        modelMapper.addConverter(new ImageConverter());
        return modelMapper;
    }

}
