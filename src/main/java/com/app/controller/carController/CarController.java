package com.app.controller.carController;

import com.app.entity.cars.Car;
import com.app.service.carService.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCar() {
        return carService.getAllCars();
    }

    @GetMapping("/paginated")
    public Page<Car> getCars(Pageable pageable){
        return carService.getCars(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCatById(@PathVariable Long id){
        Optional<Car> car= carService.getCarById(id);
        if(car.isPresent()){
            return ResponseEntity.ok(car.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        Optional<Car> car = carService.getCarById(id);
        if(car.isPresent()){
            Car updateCar=car.get();
            updateCar.setBrand(carDetails.getBrand());
            updateCar.setFuelType(carDetails.getFuelType());
            updateCar.setModel(carDetails.getModel());
            updateCar.setTransmission(carDetails.getTransmission());
            updateCar.setYear(carDetails.getYear());
            return ResponseEntity.ok(carService.saveCar(updateCar));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
