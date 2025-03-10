package com.app.service.carService;

import com.app.entity.cars.Car;
import com.app.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Page<Car> getCars(Pageable pageable){
        return carRepository.findAll(pageable);
    }

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }
}
