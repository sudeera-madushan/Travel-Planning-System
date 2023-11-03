package lk.ijse.travel.vehicleservice.exeption;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/1/2023
 * @Project : Next Travel Pvt. Ltd
 */
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}