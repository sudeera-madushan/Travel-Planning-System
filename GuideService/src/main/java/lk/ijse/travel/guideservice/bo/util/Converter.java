package lk.ijse.travel.guideservice.bo.util;

import lk.ijse.travel.guideservice.dto.GuideDTO;
import lk.ijse.travel.guideservice.entity.Guide;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/11/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;
    public Guide getGuideEntity(GuideDTO guide){
        return modelMapper.map(guide, Guide.class);
    }
    public GuideDTO getGuideDTO(Guide guide){
        return modelMapper.map(guide, GuideDTO.class);
    }

}
