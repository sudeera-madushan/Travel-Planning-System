package lk.ijse.travel.maintravelservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class Booking extends SuperEntity{
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "count_of_days")
    private int countOfDays;
    @Column(name = "count_of_nights")
    private int countOfNights;
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "total_headcount")
    private int totalHeadCount;
    @Column(name = "need_guid")
    private boolean needGuide;
    @Column(name = "with_pets")
    private boolean withPets;
    @Column(name = "package_value")
    private BigDecimal packageValue;
    @Column(name = "paid_value")
    private BigDecimal paidValue;
    private String remarks;
    @Column(name = "area_id")
    private String areaId;
    @Column(name = "guide_id")
    private String guideId;
    @Column(name = "vehicle_id")
    private String vehicleId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "package_category_id")
    private String packageCategoryId;
}
