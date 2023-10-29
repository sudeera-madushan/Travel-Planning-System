package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.AreaImageService;
import lk.ijse.travel.maintravelservice.bo.AreaService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.bo.util.ImageConverter;
import lk.ijse.travel.maintravelservice.dto.AreaDTO;
import lk.ijse.travel.maintravelservice.dto.AreaImageDTO;
import lk.ijse.travel.maintravelservice.dto.Response;
import lk.ijse.travel.maintravelservice.entity.Area;
import lk.ijse.travel.maintravelservice.entity.AreaImage;
import lk.ijse.travel.maintravelservice.persistence.AreaImageRepo;
import lk.ijse.travel.maintravelservice.persistence.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/14/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private Converter converter;
    @Autowired
    private AreaRepo areaRepo;
    @Autowired
    private AreaImageRepo areaImageRepo;
    @Override
    public Response<AreaDTO> saveArea(AreaDTO dto) {
        Area are = areaRepo.save(converter.getAreaEntity(dto));
        System.out.println(are.toString());
         areaImageRepo.saveAll(dto.getImages()
                 .stream().map(image ->
                         new AreaImage(are.getId(),Base64.getEncoder().encodeToString(image))
                 )
                 .collect(Collectors.toList()));
        return new Response<>(HttpStatus.CREATED,"Area save successfully",
                converter.getAreaDTO(
                        are
                ));
    }

    @Override
    public Response<AreaDTO> getArea(String id) {
        return new Response<>(HttpStatus.OK,"Area get successfully",
                converter.getAreaDTO(areaRepo.findById(id).get()));
    }

    @Override
    public Response<List<AreaDTO>> getAll() {
        List<AreaDTO> areas = areaRepo.findAll().stream().map(area -> converter.getAreaDTO(area)).toList();
        areas.forEach(area -> area.setImages(
                areaImageRepo.findAllByAreaId(area.getId()).stream().map(
                        image -> Base64.getDecoder().decode(image.getImage())
                ).toList()
        ));
        return new Response<>(HttpStatus.OK,"Get All Area successfully",
                areas);
    }
}
