package java16.storeegg.service.impl;

import jakarta.transaction.Transactional;
import java16.storeegg.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    public static final String UPLOAD_DIR = "/Users/baiel/Documents/uploads/";
@Override
    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file cannot be uploaded");
        }

    Files.createDirectories(Paths.get(UPLOAD_DIR));

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        Files.createDirectories(filePath.getParent());

        Files.write(filePath, file.getBytes());

        return fileName;
    }

    public byte[] getImage(String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        return Files.readAllBytes(filePath);
    }

}
