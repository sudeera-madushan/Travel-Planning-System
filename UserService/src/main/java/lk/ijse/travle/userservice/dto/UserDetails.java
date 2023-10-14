package lk.ijse.travle.userservice.dto;

import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/8/2023
 * @Project : TravelPlanningSystem
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String id;
    private int age;
    private UserDTO user;

}