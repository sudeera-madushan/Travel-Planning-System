package lk.ijse.travel.maintravelservice.bo;

import lk.ijse.travel.maintravelservice.dto.AreaDTO;
import lk.ijse.travel.maintravelservice.dto.Response;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
public interface AreaService {
    Response<AreaDTO> saveArea(AreaDTO dto);
    Response<AreaDTO> getArea(String id);

    Response<List<AreaDTO>> getAll();

    Response<List<AreaDTO>> findNearestPlaces(String id);

    Response<List<AreaDTO>> findNearestPlacesBySrc(String src);
}
