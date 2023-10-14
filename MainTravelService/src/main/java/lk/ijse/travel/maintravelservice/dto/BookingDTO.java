package lk.ijse.travel.maintravelservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String id;
    private Date startDate,endDate;
    private int countOfDays,countOfNights,noOfChildren,totalHeadCount;
    private boolean needGuide,withPets;
    private BigDecimal packageValue,paidValue;
    private String remarks,areaId,guideId,vehicleId,customerId,packageCategoryId;

    public BookingDTO(Date startDate, Date endDate, int countOfDays, int countOfNights, int noOfChildren,
                      int totalHeadCount, boolean needGuide, boolean withPets, BigDecimal packageValue,
                      BigDecimal paidValue, String remarks, String areaId, String guideId, String vehicleId,
                      String customerId, String packageCategoryId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.countOfDays = countOfDays;
        this.countOfNights = countOfNights;
        this.noOfChildren = noOfChildren;
        this.totalHeadCount = totalHeadCount;
        this.needGuide = needGuide;
        this.withPets = withPets;
        this.packageValue = packageValue;
        this.paidValue = paidValue;
        this.remarks = remarks;
        this.areaId = areaId;
        this.guideId = guideId;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.packageCategoryId = packageCategoryId;
    }
}
