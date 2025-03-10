package com.app.controller.carController;

import com.app.entity.cars.Brand;
import com.app.service.carService.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getAllBrands(){
        return  brandService.getAllBrands();
    }

    @GetMapping("/paginated")
    public Page<Brand> getBrands(Pageable pageable){
        return brandService.getBrands(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id){
        Optional<Brand> brand = brandService.getBrandById(id);
        if(brand.isPresent()){
            return new ResponseEntity<>(brand.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        brandService.saveBrand(brand);
        return new ResponseEntity<>(brand,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(
            @PathVariable Long id,
            @RequestBody Brand brandDetails
    ){
        Optional<Brand> brand = brandService.getBrandById(id);
        if(brand.isPresent()){
            Brand updateBrand = brand.get();
            updateBrand.setName(brandDetails.getName());
            return ResponseEntity.ok(brandService.saveBrand(updateBrand));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id){
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
