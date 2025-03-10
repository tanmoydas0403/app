package com.app.service.evaluation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class S3Services {

    private final S3Client s3Client;

    @Value("${bucket-name}")
    private String bucketName;

    public S3Services(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            try {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();

                PutObjectResponse response = s3Client.putObject(putObjectRequest,
                        Paths.get(file.getOriginalFilename()));

                String url = String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);
                urls.add(url);
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload file to S3", e);
            }
        }

        return urls;
    }
}
