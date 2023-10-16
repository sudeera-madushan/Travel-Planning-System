package lk.ijse.travel.hotelservice.dto;

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
    String id;
    String username,password;
    private Type role;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

