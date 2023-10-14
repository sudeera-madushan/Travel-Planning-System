package lk.ijse.travle.userservice.dto;

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
public class CustomerDTO {
    private String id;
    private int age;
    private String full_name, gender, email, contact_no,address, nic_or_passport_no;
    private byte[] nic_or_passport_image_front;
    private byte[] nic_or_passport_image_back;
    private String remarks;
    private UserDTO user;

}