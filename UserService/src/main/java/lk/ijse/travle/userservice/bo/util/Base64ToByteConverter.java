package lk.ijse.travle.userservice.bo.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Base64;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public class Base64ToByteConverter implements Converter<String, byte[]> {
    @Override
    public byte[] convert(MappingContext<String, byte[]> context) {
        String source = context.getSource();
        if (source == null) {
            return null;
        }
        return Base64.getDecoder().decode(source);
    }
}
