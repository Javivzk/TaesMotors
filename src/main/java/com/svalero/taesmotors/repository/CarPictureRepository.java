package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.CarPicture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPictureRepository extends MongoRepository<CarPicture, String> {

    List<CarPicture> findByCarId(String carId);
}
