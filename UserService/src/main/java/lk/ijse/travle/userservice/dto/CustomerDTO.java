package lk.ijse.travle.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    private String id,username,password;
    @NotNull
    @Min(value = 18, message = "Age Minimum 18")
    private int age;
    @NotNull
    private String fullName, gender, email, contactNo,address, nicOrPassportNo;
    @NotNull
    private byte[] nicOrPassportImageFront;
    @NotNull
    private byte[] nicOrPassportImageBack;
    private String remarks;
    @NotNull
    private String userId;

    public CustomerDTO( int age, String fullName, String gender, String email, String contactNo,
                       String address, String nicOrPassportNo, String remarks, String userId) {
        this.age = age;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.nicOrPassportNo = nicOrPassportNo;
        this.remarks = remarks;
        this.userId = userId;
    }
}