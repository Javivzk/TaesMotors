package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;
    @Override
    public List<Car> findAllByBrand() {
        return carRepository.findAllByBrand();
    }
}
