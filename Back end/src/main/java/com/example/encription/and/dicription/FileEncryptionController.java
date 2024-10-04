package com.example.encription.and.dicription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileEncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<byte[]> encryptFile(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] encryptedData = encryptionService.encryptFile(file.getInputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getOriginalFilename() + ".enc\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(encryptedData);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<byte[]> decryptFile(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] decryptedData = encryptionService.decryptFile(file.getInputStream());

        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null && originalFileName.endsWith(".enc")) {
            originalFileName = originalFileName.substring(0, originalFileName.length() - 4);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originalFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(decryptedData);
    }
}

