package com.svalero.taesmotors;

import com.svalero.taesmotors.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaesmotorsApplication {

	@Autowired
    CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaesmotorsApplication.class, args);
	}

}
