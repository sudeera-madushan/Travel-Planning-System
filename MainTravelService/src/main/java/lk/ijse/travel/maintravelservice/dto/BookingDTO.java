package lk.ijse.travel.maintravelservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String remarks,guideId,customerId,packageCategoryId;
    private List<AreaDTO> areaList=new ArrayList<>();
    private List<DatesDTO> dates=new ArrayList<>();
    private String vehicleId;

    public BookingDTO(Date startDate, Date endDate, int countOfDays, int countOfNights, int noOfChildren,
                      int totalHeadCount, boolean needGuide, boolean withPets, BigDecimal packageValue,
                      BigDecimal paidValue, String remarks, String guideId,
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
        this.guideId = guideId;
        this.customerId = customerId;
        this.packageCategoryId = packageCategoryId;
    }
}
