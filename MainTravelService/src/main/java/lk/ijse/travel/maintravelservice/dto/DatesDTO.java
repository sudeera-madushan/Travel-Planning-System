package lk.ijse.travel.maintravelservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/2/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatesDTO {
    private LocalDate date;
    private List<RoutesDTO> routes;
    private RoutesDTO end;
}
