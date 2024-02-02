package com.nobiz.aics_u.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static ResponseEntity<Resource> downloadAttach(String storedFullPath, String savedFileName) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + storedFullPath);
        String encodedFileName = UriUtils.encode(savedFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName +"\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
