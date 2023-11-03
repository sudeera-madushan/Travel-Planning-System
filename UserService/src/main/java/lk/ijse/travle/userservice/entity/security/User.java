package lk.ijse.travle.userservice.entity.security;

import jakarta.persistence.*;
import lk.ijse.travle.userservice.entity.SuperEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */

@ToString
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends SuperEntity implements UserDetails {

    @Column(name = "username", length = 150, nullable = false, unique = true)

    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Auth> auths=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (auths == null || auths.isEmpty()) return new ArrayList<>();
        return auths.stream().map(auth -> new SimpleGrantedAuthority(auth.getRole().getType().name())).collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}