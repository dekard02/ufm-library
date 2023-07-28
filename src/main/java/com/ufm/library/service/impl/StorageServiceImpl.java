package com.ufm.library.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

import com.ufm.library.service.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Override
    public String store(InputStream inputStream, String fileName, String dir) throws IOException {
        var path = Paths.get(dir);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        var filePath = Paths.get(dir, fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        return Paths.get(dir, fileName).toString().replace("\\", "/");
    }

    @Override
    public void delete(String filePath) {
        var path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
