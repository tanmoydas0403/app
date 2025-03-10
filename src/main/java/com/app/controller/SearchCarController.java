package com.app.controller;

import com.app.entity.cars.Car;
import com.app.repository.BrandRepository;
import com.app.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-car")
public class SearchCarController {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    public SearchCarController(BrandRepository brandRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }

    // http://localhost:8080/api/v1/search-car/cars?details=Honda&page=0&size=10&sort=year.year,asc
    @GetMapping("/cars")
    public List<Car> searchCars(
            @RequestParam String details,
            @PageableDefault(sort = "year.year", direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<Car> carPage = carRepository.searchCar(details, pageable);
        return carPage.getContent();
    }
}
