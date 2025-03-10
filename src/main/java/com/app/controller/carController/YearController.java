package com.app.controller.carController;

import com.app.entity.cars.Year;
import com.app.service.carService.YearService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/years")
public class YearController {
    private final YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping
    public List<Year> getAllYears(){
        return yearService.getAllYears();
    }

    @GetMapping("/paginated")
    public Page<Year> getYears(Pageable pageable){
        return yearService.getYears(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Year> getYearById(@PathVariable Long id){
        Optional<Year> year = yearService.getYearById(id);
        return year.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Year createYear(@RequestBody Year year){
        return yearService.saveYear(year);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Year> updateYear(
            @PathVariable Long id,
            @RequestBody Year yearDetails
    ){
        Optional<Year> year = yearService.getYearById(id);
        if(year.isPresent()){
            Year updatedYear = year.get();
            updatedYear.setYear(yearDetails.getYear());
            return ResponseEntity.ok(yearService.saveYear(updatedYear));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYear(@PathVariable Long id){
        yearService.deleteYear(id);
        return ResponseEntity.noContent().build();
    }
}
