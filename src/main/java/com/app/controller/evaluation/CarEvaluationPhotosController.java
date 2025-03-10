package com.app.controller.evaluation;

import com.app.entity.evaluation.CarDetailedEvaluation;
import com.app.entity.evaluation.CarEvaluationPhotos;
import com.app.service.evaluation.CarEvaluationPhotosService;
import com.app.service.evaluation.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-evaluation-photos")
public class CarEvaluationPhotosController {

    @Autowired
    private CarEvaluationPhotosService carEvaluationPhotosService;

    @Autowired
    private S3Services s3Service;

    @PostMapping
    public ResponseEntity<List<CarEvaluationPhotos>> createCarEvaluationPhotos(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("carDetailedEvaluationId") Long carDetailedEvaluationId) {

        List<String> urls = s3Service.uploadFiles(files);

        List<CarEvaluationPhotos> carEvaluationPhotosList = new ArrayList<>();
        for (String url : urls) {
            CarEvaluationPhotos carEvaluationPhotos = new CarEvaluationPhotos();
            carEvaluationPhotos.setUrl(url);

            CarDetailedEvaluation carDetailedEvaluation = new CarDetailedEvaluation();
            carDetailedEvaluation.setId(carDetailedEvaluationId);
            carEvaluationPhotos.setCarDetailedEvaluation(carDetailedEvaluation);

            carEvaluationPhotosList.add(carEvaluationPhotos);
        }

        List<CarEvaluationPhotos> createdCarEvaluationPhotosList = carEvaluationPhotosService.createCarEvaluationPhotos(carEvaluationPhotosList);
        return ResponseEntity.ok(createdCarEvaluationPhotosList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEvaluationPhotos> getCarEvaluationPhotoById(@PathVariable Long id) {
        Optional<CarEvaluationPhotos> carEvaluationPhotos = carEvaluationPhotosService.getCarEvaluationPhotoById(id);
        return carEvaluationPhotos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarEvaluationPhotos>> getAllCarEvaluationPhotos() {
        List<CarEvaluationPhotos> carEvaluationPhotos = carEvaluationPhotosService.getAllCarEvaluationPhotos();
        return ResponseEntity.ok(carEvaluationPhotos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarEvaluationPhoto(@PathVariable Long id) {
        carEvaluationPhotosService.deleteCarEvaluationPhoto(id);
        return ResponseEntity.noContent().build();
    }
}