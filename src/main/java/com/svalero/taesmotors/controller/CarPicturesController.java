package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.CarPicture;
import com.svalero.taesmotors.repository.CarPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CarPicturesController {

    @Autowired
    private CarPictureRepository carsPicturesRepository;

    @PostMapping("/upload/{carId}")
    public ResponseEntity<String> uploadCarPicture(@PathVariable long carId, @RequestParam("pictureFile")MultipartFile pictureFile) {
        try {
            byte[] pictureData = pictureFile.getBytes();

            CarPicture carsPictures = new CarPicture();
            carsPictures.setCarId(carId);
            carsPictures.setImageData(pictureData);

            carsPicturesRepository.save(carsPictures);

            return ResponseEntity.ok("Imagen guardada correctamente");
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }
}
