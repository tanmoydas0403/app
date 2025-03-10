package com.app.controller.evaluation;

import com.app.service.evaluation.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car-evaluation-photo")
public class FileUploadController {

    @Autowired
    private S3Services s3Service;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        List<String> urls = s3Service.uploadFiles(files);
        return ResponseEntity.ok(urls);
    }
}
