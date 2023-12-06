package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();

    List<Car> findByModel(String model);

    List<Car> findByBrand(String brand);

    List<Car> findByBrandAndModel(String brand, String model);

}
