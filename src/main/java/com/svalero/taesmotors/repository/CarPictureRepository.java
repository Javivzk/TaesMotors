package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.CarPicture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarPictureRepository extends CrudRepository<CarPicture, Long> {

    List<CarPicture> findByCarId(long carId);
}
