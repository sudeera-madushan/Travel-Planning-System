package lk.ijse.travel.guideservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/10/2023
 * @Project : TravelPlanningSystem
 */
@AllArgsConstructor
@NoArgsConstructor
public class Auth implements GrantedAuthority {
    public String str;
    @Override
    public String getAuthority() {
        return str;
    }
}
