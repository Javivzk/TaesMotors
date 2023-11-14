package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.CarPicture;

import java.util.List;

public interface CarPictureService {
    List<CarPicture> findByCarId(long carId);
}
