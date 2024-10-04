package com.example.encription.and.dicription;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private SecretKeySpec secretKey;

    public EncryptionService() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        secretKey = new SecretKeySpec(key.getEncoded(), ALGORITHM);
    }

    public byte[] encryptFile(InputStream inputStream) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                byteArrayOutputStream.write(output);
            }
        }

        byte[] outputBytes = cipher.doFinal();
        byteArrayOutputStream.write(outputBytes);

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] decryptFile(InputStream inputStream) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                byteArrayOutputStream.write(output);
            }
        }

        byte[] outputBytes = cipher.doFinal();
        byteArrayOutputStream.write(outputBytes);

        return byteArrayOutputStream.toByteArray();
    }
}
