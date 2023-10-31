package lk.ijse.travel.maintravelservice.bo.impl;

import lk.ijse.travel.maintravelservice.bo.AreaImageService;
import lk.ijse.travel.maintravelservice.bo.AreaService;
import lk.ijse.travel.maintravelservice.bo.util.Converter;
import lk.ijse.travel.maintravelservice.bo.util.DistanceMatrixCalculator;
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

import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public Response<List<AreaDTO>> findNearestPlaces(String id) {
        Area area = areaRepo.findById(id).get();
        List<Area> areas = areaRepo.findAll();
        List<Area> matchers = new ArrayList<>();
        try {
            for (Area a : areas) {
                if (!a.getId().equalsIgnoreCase(area.getId())) {
                    double distance = DistanceMatrixCalculator.getDistance(area.getAreaLocation(), a.getAreaLocation());
                    if (30 > distance) {
                        matchers.add(a);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Response<>(HttpStatus.OK,"Get All Nearest Areas successfully",
                matchers.stream().map(s -> converter.getAreaDTO(s)).toList());
    }

    @Override
    public Response<List<AreaDTO>> findNearestPlacesBySrc(String src) {
        List<Area> areas = areaRepo.findAll();
        List<Area> matchers = new ArrayList<>();
        try {
            for (Area a : areas) {
                    double distance = DistanceMatrixCalculator.getDistance(src, a.getAreaLocation());
                    if (30 > distance) {
                        matchers.add(a);
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Response<>(HttpStatus.OK,"Get All Nearest Areas successfully",
                matchers.stream().map(s -> converter.getAreaDTO(s)).toList());
    }

}
