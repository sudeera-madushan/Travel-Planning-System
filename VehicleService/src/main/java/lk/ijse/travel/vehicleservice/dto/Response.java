package lk.ijse.travel.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
public class Response<T> {
    private Integer status;
    private String message;
    private String description;
    private T object;

    public Response(HttpStatus httpStatus, String description, T object) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.description = description;
        this.object = object;
    }
    @JsonCreator
    public Response(
            @JsonProperty("status") Integer status,
            @JsonProperty("message") String message,
            @JsonProperty("description") String description,
            @JsonProperty("object") T object
    ) {
        this.status = status;
        this.message = message;
        this.description = description;
        this.object = object;
    }
}
