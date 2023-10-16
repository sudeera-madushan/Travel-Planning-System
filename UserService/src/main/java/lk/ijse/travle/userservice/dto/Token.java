package lk.ijse.travle.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/16/2023
 * @Project : Next Travel Pvt. Ltd
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    private String token;
    private List<Type> role;
}
