package com.app.service.evaluation;

import com.app.entity.evaluation.CarDetailedEvaluation;
import com.app.repository.evaluation.CarDetailedEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarDetailedEvaluationService {

    @Autowired
    private CarDetailedEvaluationRepository carDetailedEvaluationRepository;

    public CarDetailedEvaluation createCarDetailedEvaluation(CarDetailedEvaluation carDetailedEvaluation) {
        return carDetailedEvaluationRepository.save(carDetailedEvaluation);
    }

    public Optional<CarDetailedEvaluation> getCarDetailedEvaluationById(Long id) {
        return carDetailedEvaluationRepository.findById(id);
    }

    public List<CarDetailedEvaluation> getAllCarDetailedEvaluations() {
        return carDetailedEvaluationRepository.findAll();
    }

    public void deleteCarDetailedEvaluation(Long id) {
        carDetailedEvaluationRepository.deleteById(id);
    }
}