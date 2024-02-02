package com.nobiz.aics_u.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {
    private static final int TOKEN_LENGTH = 32;
    public static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public static boolean validateToken(String token) {
        return token != null && token.length() == TOKEN_LENGTH;
    }

}
