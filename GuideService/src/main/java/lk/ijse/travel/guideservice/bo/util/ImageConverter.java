package lk.ijse.travel.guideservice.bo.util;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Component
public class ImageConverter extends AbstractConverter<byte[], String> {
    @Override
    protected String convert(byte[] source) {
        if (source == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(source);
    }
}