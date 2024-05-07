package com.example.CharityOrganization.utils.encryption;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class SingleDESEncryption {

    // Define a fixed key for encryption and decryption
    private static final byte[] DES_KEY = {
        0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xAB, (byte)0xCD, (byte)0xEF
    };

    private static final SecretKey SECRET_KEY = new SecretKeySpec(DES_KEY, "DES");

    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }
}
