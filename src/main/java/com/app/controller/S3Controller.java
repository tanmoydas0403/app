package com.app.controller;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.repository.CarImageRepository;
import com.app.repository.CarRepository;
import com.app.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/s3")
public class S3Controller {
    private final S3Service s3Service;
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;

    public S3Controller(S3Service s3Service, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.s3Service = s3Service;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    // Method to upload a file
    @PostMapping("/upload")
    public ResponseEntity<CarImage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("carId") Long carId) throws IOException {
        String keyName = file.getOriginalFilename();
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        String url = s3Service.uploadFile(keyName, tempFile.getAbsolutePath());

        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            CarImage image = new CarImage();
            image.setUrl(url);
            image.setCar(car);
            CarImage savedImage = carImageRepository.save(image);
            return new ResponseEntity<>(savedImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to download a file
    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("keyName") String keyName, @RequestParam("downloadPath") String downloadPath) {
        try {
            s3Service.downloadFile(keyName, downloadPath);
            return new ResponseEntity<>("File downloaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File download failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}