package com.example.smartplot.service;

import com.example.smartplot.dto.UploadResponse;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_TYPES = Set.of(".jpg", ".jpeg", ".png", ".webp");

    private final UserRepository userRepository;
    private final Path uploadPath;

    public FileStorageService(UserRepository userRepository, @Value("${app.upload.dir:uploads}") String uploadDir) throws IOException {
        this.userRepository = userRepository;
        this.uploadPath = resolveUploadPath(uploadDir);

        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException ex) {
            throw new IOException("Unable to create upload directory at " + this.uploadPath, ex);
        }
    }

    public UploadResponse uploadPlotImage(MultipartFile file, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only ADMIN accounts can upload plot images");
        }

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }

        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);

        if (!ALLOWED_TYPES.contains(extension)) {
            throw new IllegalArgumentException("Only JPG, JPEG, PNG, and WEBP files are supported");
        }

        String fileName = UUID.randomUUID() + extension;
        Path target = uploadPath.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to store image file");
        }

        return new UploadResponse("/uploads/" + fileName);
    }

    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
    }

    private Path resolveUploadPath(String uploadDir) {
        Path configuredPath = Paths.get(uploadDir);
        if (configuredPath.isAbsolute()) {
            return configuredPath.normalize();
        }

        return Paths.get(System.getProperty("user.home"), ".smartplot-portal", uploadDir)
                .toAbsolutePath()
                .normalize();
    }
}
