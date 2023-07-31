package com.ufm.library.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FilenameHelper {

    public String getMultipartFileExtension(MultipartFile file) {
        var splitingFileName = file.getOriginalFilename().split("\\.");
        var fileExtension = splitingFileName[splitingFileName.length - 1];
        return fileExtension;
    }

}
