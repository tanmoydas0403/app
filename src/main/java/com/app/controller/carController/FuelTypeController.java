package com.app.controller.carController;

import com.app.entity.cars.FuelType;
import com.app.service.carService.FuelTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fuel-types")
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    @GetMapping
    public List<FuelType> getAllFuelTypes(){
        return fuelTypeService.getAllFuelTypes();
    }

    @GetMapping("/paginated")
    public Page<FuelType> getFuelTypes(Pageable pageable){
        return fuelTypeService.getFuelTypes(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelType> getFuelTypeById(@PathVariable Long id){
        Optional<FuelType> fuelType = fuelTypeService.getFuelTypeById(id);
        return fuelType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FuelType createFuelType(@RequestBody FuelType fuelType){
        return fuelTypeService.saveFuelType(fuelType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelType> updateFuelType(@PathVariable Long id, @RequestBody FuelType fuelTypeDetails){
        Optional<FuelType> fuelType = fuelTypeService.getFuelTypeById(id);
        if(fuelType.isPresent()){
            FuelType updatedFuelType = fuelType.get();
            updatedFuelType.setFuelType(fuelTypeDetails.getFuelType());
            return ResponseEntity.ok(fuelTypeService.saveFuelType(updatedFuelType));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteFuelType(@PathVariable Long id){
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity.noContent().build();
    }
}
