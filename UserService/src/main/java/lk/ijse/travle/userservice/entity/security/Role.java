package lk.ijse.travle.userservice.entity.security;

import jakarta.persistence.*;
import lk.ijse.travle.userservice.dto.Type;
import lk.ijse.travle.userservice.entity.SuperEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role extends SuperEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM ('ROLE_USER','ROLE_ADMIN','ROLE_HOTEL','ROLE_GUIDE','ROLE_VEHICLE','ROLE_BOOKING') NOT NULL")
    private Type type;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Auth> auths=new ArrayList<>();

    public Role(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Role(int id) {
        this.id = id;
    }
}