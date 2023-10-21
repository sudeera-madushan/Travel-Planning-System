package lk.ijse.travel.guideservice.bo.impl;

import lk.ijse.travel.guideservice.bo.GuideService;
import lk.ijse.travel.guideservice.bo.util.Converter;
import lk.ijse.travel.guideservice.dto.GuideDTO;
import lk.ijse.travel.guideservice.dto.Response;
import lk.ijse.travel.guideservice.entity.Guide;
import lk.ijse.travel.guideservice.persistence.GuideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideRepo guideRepo;
    @Autowired
    private Converter converter;
    @Transactional
    @Override
    public Response<GuideDTO> save(GuideDTO guideDTO) {

        return new Response<GuideDTO>(HttpStatus.CREATED,"User Save Successfully",
                converter.getGuideDTO(
                        guideRepo.save(
                                converter.getGuideEntity(guideDTO)
                        )
                ));
    }

    @Transactional
    @Override
    public Response<GuideDTO> update(GuideDTO guideDTO) {

        return  new Response<GuideDTO>(HttpStatus.CREATED,"User Update Successfully",
                converter.getGuideDTO(
                        guideRepo.save(
                                converter.getGuideEntity(guideDTO)
                        )
                ));
    }

    @Override
    public Response<GuideDTO> get(String id) {
        return new Response<>(HttpStatus.OK,"User find successfully",
                converter.getGuideDTO(guideRepo.findById(id).get()));
    }

    @Override
    public Response<String> delete(String id) {
        guideRepo.deleteById(id);
        return new Response<>(HttpStatus.OK,"User Delete successfully","");
    }

    @Override
    public Response<List<GuideDTO>> getAll() {
        return new Response<>(HttpStatus.OK,"Get all guide list successfully",
                guideRepo.findAll().stream().map(
                        guide -> converter.getGuideDTO(guide)
                ).toList());
    }
}
