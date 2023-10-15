package lk.ijse.travle.userservice.entity;

import jakarta.persistence.*;
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
    @Column(name = "full_name")
    private String fullName;
    private String gender;
    private String email;
    @Column(name = "contact_no")
    private String contactNo;
    private String address;
    @Column(name = "nic_or_passport_no")
    private String nicOrPassportNo;
    @Column(name = "nic_or_passport_image_front", columnDefinition = "LONGTEXT")
    private String nicOrPassportImageFront;
    @Column(name = "nic_or_passport_image_back", columnDefinition = "LONGTEXT")
    private String nicOrPassportImageBack;
    @Column(columnDefinition = "LONGTEXT")
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
