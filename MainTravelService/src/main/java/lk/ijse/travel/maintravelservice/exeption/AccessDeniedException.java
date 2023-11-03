package lk.ijse.travel.maintravelservice.exeption;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/3/2023
 * @Project : Next Travel Pvt. Ltd
 */
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String massage) {
        super(massage);
    }
}
