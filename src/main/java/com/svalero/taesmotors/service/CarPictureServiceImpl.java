package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.CarPicture;
import com.svalero.taesmotors.repository.CarPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarPictureServiceImpl implements CarPictureService {

    @Autowired
    CarPictureRepository carsPicturesRepository;

    @Override
    public List<CarPicture> findByCarId(String carId) {
        return carsPicturesRepository.findByCarId(carId);
    }

}
