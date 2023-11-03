package lk.ijse.travel.maintravelservice.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/1/2023
 * @Project : Next Travel Pvt. Ltd
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String message;

}
