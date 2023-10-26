package lk.ijse.travel.hotelservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel extends SuperEntity{
    private String name,category,location;
    private String email;
    @Column(name = "map_location")
    private String mapLocation;
    @Column(name = "contact_no_one")
    private String contactNoOne;
    @Column(name = "contact_no_two")
    private String contactNoTwo;
    @Column(name = "pet_is_allowed")
    private boolean petIsAllowed;
    @Column(name = "hotel_fee")
    private BigDecimal hotelFee;
    @Column(name = "cancellation_criteria_is_free")
    private boolean cancellationCriteriaIsFree;
    @Column(name = "cancellation_fee")
    private BigDecimal cancellationFee;
    @Column(name = "package_category_id")
    private String packageCategoryId;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelImage> hotelImages = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "options",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<HotelOption> options= new ArrayList<>();

}
