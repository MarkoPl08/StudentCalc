package com.grade.studentcalc.utils;

import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateSecretKey {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();
        String base64Secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Base64 Secret Key: " + base64Secret);
    }
}
