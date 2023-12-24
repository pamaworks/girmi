package com.girmi.jwt.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptUtil {
    public static String sha256Key = "girmi";
    public static String getEncryptSha256(String text) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((text+sha256Key).getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
