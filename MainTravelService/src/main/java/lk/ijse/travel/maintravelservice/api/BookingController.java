package lk.ijse.travel.maintravelservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.travel.maintravelservice.bo.AreaService;
import lk.ijse.travel.maintravelservice.bo.BookingService;
import lk.ijse.travel.maintravelservice.bo.util.DistanceMatrixCalculator;
import lk.ijse.travel.maintravelservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private final WebClient.Builder webClientBuilder;
    private final BookingService bookingService;
    private final AreaService areaService;
    @RequestMapping("save")
    @PostMapping
    public Response<BookingDTO> saveBooking(@RequestBody BookingDTO dto){
        System.out.println(dto);
        return bookingService.saveBooking(dto);
    }
    @GetMapping
    public Response<BookingDTO> getBooking(@RequestParam String id){
        return bookingService.getBooking(id);
    }

    @RequestMapping("distance")
    @GetMapping
    public Response<double[]> getTravelDistance(@RequestBody BookingDTO dto, @RequestParam String token){

        String src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15853.974487908694!2d79.94707162171555!3d6.585395306654828!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae23745252e0b23%3A0x744c4387ace9dd5a!2sHansana%20Tours%20%26%20Travels!5e0!3m2!1sen!2slk!4v1698933749457!5m2!1sen!2slk";
        double[] data= new double[2];
        double distance=0;
        double cost=0;
        try {
            for (DatesDTO date : dto.getDates()) {
                double day=0;
                System.out.println(dto.getVehicleId());
                VehicleDTO vehicle = getVehicle(dto.getVehicleId());
                for (RoutesDTO route : date.getRoutes()) {
                    System.out.println(route);
                    if (route.getType().equalsIgnoreCase("AREA")) {
                        AreaDTO area = areaService.findById(route.getId());
                        double dis = DistanceMatrixCalculator.getDistance(src, area.getAreaLocation());
                        day+=dis;
                        src=area.getAreaLocation();
                    } else if (route.getType().equalsIgnoreCase("HOTEL")) {
                        ResponseHotel hotel = getHotel(route.getId(), token);
                        double dis = DistanceMatrixCalculator.getDistance(src, hotel.getMapLocation());
                        day+=dis;
                        src=hotel.getMapLocation();
                    }
                }
                RoutesDTO end = date.getEnd();
                if (end.getType().equalsIgnoreCase("AREA")) {
                    AreaDTO area = areaService.findById(end.getId());
                    double dis = DistanceMatrixCalculator.getDistance(src, area.getAreaLocation());
                    day+=dis;
                    src=area.getAreaLocation();
                } else if (end.getType().equalsIgnoreCase("HOTEL")) {
                    ResponseHotel hotel = getHotel(end.getId(), token);
                    double dis = DistanceMatrixCalculator.getDistance(src, hotel.getMapLocation());
                    day+=dis;
                    src=hotel.getMapLocation();
                }
                distance+=day;
                if (day>100) {
                    cost+=vehicle.getFeeForDay().doubleValue();
                    day-=100;
                    cost+=day*vehicle.getFeeForKm().doubleValue();
                }else {
                    cost+=vehicle.getFeeForDay().doubleValue();
                }

            }
            data[0]=distance;
            data[1]=cost;
            return new Response<>(HttpStatus.OK,"Distance Calculate Successfully",data);
        }catch (Exception e){
            e.printStackTrace();
            return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR,"Distance Calculate Error",null);
        }
    }

    private VehicleDTO getVehicle(String vehicleId) {
        WebClient webClient = WebClient.create("http://localhost:8093/travel/api/v1/vehicle/getBy?id=" + vehicleId);
        Mono<Response<VehicleDTO>> orderMono = webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
        return orderMono.block().getObject();
    }

    private ResponseHotel getHotel(String id, String token) {
        WebClient webClient = WebClient.create("http://localhost:8094/travel/api/v1/hotel/getBy?id=" + id);
        Mono<Response<ResponseHotel>> orderMono = webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<Response<ResponseHotel>>() {});
        return orderMono.block().getObject();
    }


}
