package com.ncst.cack.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 通用工具类
 * @author liuxuelian
 * @date 2021/04/02
 */
public class Commons {
    // 计算密码强度
    public static int getPasswordLevel(String password) {
        int passwordLength = password.length();
        int passwordLevel = 0; // 0-弱， 1-中等， 2-强
        boolean hasLower = false,
                hasUpper = false,
                hasDigit = false,
                specialChar = false;
        Set<Character> set = new HashSet<>(Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                '*', '(', ')', '-', '+'));

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (set.contains(c)) {
                specialChar = true;
            }
        }

        if (hasDigit && hasLower && hasUpper && specialChar && (passwordLength > 8)) {
            passwordLevel = 2;
        } else if (hasLower || hasUpper || specialChar) {
            passwordLevel = 1;
        }
        return passwordLevel;
    }

    // 密码比较是否一致
    public static Boolean matchesPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    // 密码加盐
    public static String encoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
