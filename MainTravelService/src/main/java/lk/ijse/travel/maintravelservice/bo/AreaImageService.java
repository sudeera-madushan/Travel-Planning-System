package lk.ijse.travel.maintravelservice.bo;

import lk.ijse.travel.maintravelservice.dto.AreaImageDTO;
import lk.ijse.travel.maintravelservice.dto.Response;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface AreaImageService {
    Response<AreaImageDTO> saveAreaImage(AreaImageDTO dto);
    Response<AreaImageDTO> getAreaImage(String id);
    public Response<List<AreaImageDTO>> getAllByAreaId(String id);
}
