package com.example.smartplot.controller;

import com.example.smartplot.dto.UploadResponse;
import com.example.smartplot.service.FileStorageService;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class UploadController {

    private final FileStorageService fileStorageService;

    public UploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploads/images")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadResponse uploadPlotImage(@RequestParam("file") MultipartFile file, Principal principal) {
        return fileStorageService.uploadPlotImage(file, principal.getName());
    }
}
