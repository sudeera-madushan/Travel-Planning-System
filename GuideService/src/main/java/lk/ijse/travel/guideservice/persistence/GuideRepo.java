package lk.ijse.travel.guideservice.persistence;

import lk.ijse.travel.guideservice.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/13/2023
 * @Project : Next Travel Pvt. Ltd
 */
@Repository
public interface GuideRepo extends MongoRepository<Guide,String> {
}
