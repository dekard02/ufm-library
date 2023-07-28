package com.ufm.library.service;

import java.io.IOException;
import java.io.InputStream;

public interface StorageService {

    public String store(InputStream inputStream, String fileName, String dir) throws IOException;

    public void delete(String filePath);
}
