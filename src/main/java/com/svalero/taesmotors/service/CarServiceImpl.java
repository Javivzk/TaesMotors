package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.ClientNotFoundException;
import com.svalero.taesmotors.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public Car findById(long carId) throws CarNotFoundException {
        logger.info("Car id: " + carId);
        return carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
    }

    @Override
    public Car modifyCar(long carId, Car newCar) throws CarNotFoundException {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        logger.info("Car to modify: " + existingCar);
        existingCar.setBrand(newCar.getBrand());
        existingCar.setModel(newCar.getModel());
        existingCar.setMotor(newCar.getMotor());
        existingCar.setCombustible(newCar.getCombustible());
        existingCar.setColor(newCar.getColor());

        logger.info("Car modified: " + newCar);
        return carRepository.save(existingCar);
    }

    public Car patchCar(long carId, Map<String, Object> updates) throws CarNotFoundException {
        Car existingCar = findById(carId);

        if (updates.containsKey("brand")) {
            existingCar.setBrand((String) updates.get("brand"));
        }
        if (updates.containsKey("model")) {
            existingCar.setModel((String) updates.get("model"));
        }
        if (updates.containsKey("motor")) {
            existingCar.setMotor((String) updates.get("motor"));
        }
        if (updates.containsKey("combustible")) {
            existingCar.setCombustible((String) updates.get("combustible"));
        }
        if (updates.containsKey("color")) {
            existingCar.setColor((String) updates.get("color"));
        }
        return modifyCar(carId, existingCar);
    }
}
