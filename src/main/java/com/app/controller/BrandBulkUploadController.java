package com.app.controller;

import com.app.entity.cars.Brand;
import com.app.repository.BrandRepository;
import com.app.service.BulkCarUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/brand/bulk-upload")
public class BrandBulkUploadController {

   private BrandRepository brandRepository;
   private final BulkCarUploadService bulkCarUploadService;

    public BrandBulkUploadController(BrandRepository brandRepository, BulkCarUploadService bulkCarUploadService) {
        this.brandRepository = brandRepository;
        this.bulkCarUploadService = bulkCarUploadService;
    }

    @PostMapping("/upload")
    public String uploadExcelFile(){
        String filePath="D:\\Book1.xlsx";
        try{
            List<Brand> brands =
                    bulkCarUploadService.readExcel(filePath);
            brandRepository.saveAll(brands);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return "uploaded..!";
    }
}
