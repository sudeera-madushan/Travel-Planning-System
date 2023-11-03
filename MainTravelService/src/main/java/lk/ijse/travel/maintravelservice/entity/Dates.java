package lk.ijse.travel.maintravelservice.entity;

import jakarta.persistence.Entity;
import lk.ijse.travel.maintravelservice.dto.RoutesDTO;
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
@Entity
public class Dates extends SuperEntity{
    private LocalDate date;
    private List<RoutesDTO> routes;
    private RoutesDTO end;
}
