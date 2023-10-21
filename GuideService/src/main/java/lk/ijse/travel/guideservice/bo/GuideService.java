package lk.ijse.travel.guideservice.bo;

import lk.ijse.travel.guideservice.dto.GuideDTO;
import lk.ijse.travel.guideservice.dto.Response;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface GuideService {

    Response<GuideDTO> save(GuideDTO guideDTO);
    Response<GuideDTO> get(String id);
    Response<String> delete(String id);

    Response<List<GuideDTO>> getAll();

}
