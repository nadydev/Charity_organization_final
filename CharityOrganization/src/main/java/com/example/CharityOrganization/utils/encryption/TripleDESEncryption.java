package com.example.CharityOrganization.utils.encryption;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class TripleDESEncryption {

    // Define a static key for encryption and decryption
    private static final String DES_KEY_STRING = "0123456789ABCDEF10325476"; // Adjusted to 24 characters (48 hex digits)
    private static final byte[] DES_KEY = DES_KEY_STRING.getBytes();
    private static final SecretKey SECRET_KEY = new SecretKeySpec(DES_KEY, "DESede");

    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }
}
