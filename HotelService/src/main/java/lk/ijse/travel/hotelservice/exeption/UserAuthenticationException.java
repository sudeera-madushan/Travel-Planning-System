package lk.ijse.travel.hotelservice.exeption;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/3/2023
 * @Project : Next Travel Pvt. Ltd
 */
public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException(String message) {
        super(message);
    }
}