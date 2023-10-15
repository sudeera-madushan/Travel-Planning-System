package lk.ijse.travle.userservice.bo;


import lk.ijse.travle.userservice.dto.Response;
import lk.ijse.travle.userservice.dto.UserDTO;
import lk.ijse.travle.userservice.entity.security.User;

/**
 * author : Sudeera Madushan
 * date : 10/15/2023
 * project : TravelPlanningSystem
 */
public interface AuthenticationService {
    Response<String> authenticate(UserDTO user);
    String checkAuth(String token);
}
