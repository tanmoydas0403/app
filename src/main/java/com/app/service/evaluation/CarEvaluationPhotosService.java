package com.app.service.evaluation;

import com.app.entity.evaluation.CarEvaluationPhotos;
import com.app.repository.evaluation.CarEvaluationPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarEvaluationPhotosService {

    @Autowired
    private CarEvaluationPhotosRepository carEvaluationPhotosRepository;

    public List<CarEvaluationPhotos> createCarEvaluationPhotos(List<CarEvaluationPhotos> carEvaluationPhotosList) {
        return carEvaluationPhotosRepository.saveAll(carEvaluationPhotosList);
    }

    public Optional<CarEvaluationPhotos> getCarEvaluationPhotoById(Long id) {
        return carEvaluationPhotosRepository.findById(id);
    }

    public List<CarEvaluationPhotos> getAllCarEvaluationPhotos() {
        return carEvaluationPhotosRepository.findAll();
    }

    public void deleteCarEvaluationPhoto(Long id) {
        carEvaluationPhotosRepository.deleteById(id);
    }
}