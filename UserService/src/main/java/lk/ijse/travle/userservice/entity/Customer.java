package lk.ijse.travle.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lk.ijse.travle.userservice.entity.security.User;
import lombok.*;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends SuperEntity{
    private int age;
    private String full_name, gender, email, contact_no,address, nic_or_passport_no;
    private byte[] nic_or_passport_image_front;
    private byte[] nic_or_passport_image_back;
    @Column(columnDefinition = "Text")
    private String remarks;
    @OneToOne
    private User user;
}
