package lk.ijse.travel.hotelservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    @NotNull(message = "Username can't be null")
    @Size(min = 6, message = "Username least 6 characters")
    private String username;

    @NotNull(message = "Password can't be null")
    @Size(min = 6, message = "Password least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    private Type role;

}

