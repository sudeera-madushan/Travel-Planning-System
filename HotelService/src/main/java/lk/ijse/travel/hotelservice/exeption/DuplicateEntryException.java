package lk.ijse.travel.hotelservice.exeption;

import lombok.Getter;

/**
 * @Author : Sudeera Madushan
 * @Date : 11/1/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
