package com.app.service.carService;

import com.app.entity.cars.FuelType;
import com.app.repository.FuelTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeService {
    private final FuelTypeRepository fuelTypeRepository;

    public FuelTypeService(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public List<FuelType> getAllFuelTypes(){
        return fuelTypeRepository.findAll();
    }

    public Page<FuelType> getFuelTypes(Pageable pageable){
        return fuelTypeRepository.findAll(pageable);
    }

    public Optional<FuelType> getFuelTypeById(Long id){
        return fuelTypeRepository.findById(id);
    }

    public FuelType saveFuelType(FuelType fuelType){
        return fuelTypeRepository.save(fuelType);
    }

    public void deleteFuelType(Long id){
        fuelTypeRepository.deleteById(id);
    }
}
