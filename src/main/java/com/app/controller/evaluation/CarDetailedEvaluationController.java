package com.app.controller.evaluation;

import com.app.entity.evaluation.CarDetailedEvaluation;
import com.app.service.evaluation.CarDetailedEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-detailed-evaluations")
public class CarDetailedEvaluationController {

    @Autowired
    private CarDetailedEvaluationService carDetailedEvaluationService;

    @PostMapping
    public ResponseEntity<CarDetailedEvaluation> createCarDetailedEvaluation(@RequestBody CarDetailedEvaluation carDetailedEvaluation) {
        CarDetailedEvaluation createdCarDetailedEvaluation = carDetailedEvaluationService.createCarDetailedEvaluation(carDetailedEvaluation);
        return ResponseEntity.ok(createdCarDetailedEvaluation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetailedEvaluation> getCarDetailedEvaluationById(@PathVariable Long id) {
        Optional<CarDetailedEvaluation> carDetailedEvaluation = carDetailedEvaluationService.getCarDetailedEvaluationById(id);
        return carDetailedEvaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarDetailedEvaluation>> getAllCarDetailedEvaluations() {
        List<CarDetailedEvaluation> carDetailedEvaluations = carDetailedEvaluationService.getAllCarDetailedEvaluations();
        return ResponseEntity.ok(carDetailedEvaluations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarDetailedEvaluation(@PathVariable Long id) {
        carDetailedEvaluationService.deleteCarDetailedEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}