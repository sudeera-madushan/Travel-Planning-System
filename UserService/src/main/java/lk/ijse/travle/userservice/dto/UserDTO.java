package lk.ijse.travle.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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


    String id;
    @NotBlank
    @NotNull(message = "Username can't be null")
    @Size(min = 6, message = "Username least 6 characters")
    private String username;
    @NotNull(message = "Password can't be null")
    @Size(min = 6, message = "Password least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    private List<Type> role=new ArrayList<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, String password, List<Type> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

