package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.AreaImageService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.dto.AreaImageDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lk.ijse.travel.maintravelservice.entity.AreaImage;
import lk.ijse.travel.maintravelservice.persistence.AreaImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class AreaImageServiceImpl implements AreaImageService {
    @Autowired
    private Converter converter;
    @Autowired
    private AreaImageRepo areaImageRepo;
    @Transactional
    @Override
    public Response<AreaImageDTO> saveAreaImage(AreaImageDTO dto) {
        return new Response<>(HttpStatus.CREATED,"Area Image save successfully",
                converter.getAreaImageDTO(
                        areaImageRepo.save(
                                converter.getAreaImageEntity(dto)
                        )
                ));
    }

    @Override
    public Response<AreaImageDTO> getAreaImage(String id) {
        return new Response<>(HttpStatus.OK,"Area Image get successfully",
                converter.getAreaImageDTO(
                        areaImageRepo.findById(id).get()
                ));
    }

    @Override
    public Response<List<AreaImageDTO>> getAllByAreaId(String id){
        return new Response<>(HttpStatus.OK,"Area Image List get successfully",
                converter.getAreaImageDTO(areaImageRepo.findAllByAreaId(id)));
    }
}
