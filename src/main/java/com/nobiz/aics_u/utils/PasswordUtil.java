package com.nobiz.aics_u.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * 랜덤 패스워드 생성 유틸
 * String generatePassword(자리수) 요청
 */
public class PasswordUtil {
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*";
    private static final String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;

    public static String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        // Ensure at least one character from each character set
        builder.append(getRandomChar(LOWERCASE_CHARS, random));
        builder.append(getRandomChar(UPPERCASE_CHARS, random));
        builder.append(getRandomChar(DIGITS, random));
        builder.append(getRandomChar(SPECIAL_CHARS, random));

        // Fill the rest of the password with random characters
        for (int i = 4; i < length; i++) {
            builder.append(getRandomChar(ALL_CHARS, random));
        }

        return builder.toString();
    }

    private static char getRandomChar(String charSet, SecureRandom random) {
        int index = random.nextInt(charSet.length());
        return charSet.charAt(index);
    }

    public static String encodePassword(String passwd, PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(passwd);
    }

}
