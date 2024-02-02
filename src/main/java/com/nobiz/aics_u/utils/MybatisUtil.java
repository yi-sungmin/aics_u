package com.nobiz.aics_u.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class MybatisUtil {

    public static boolean isEmpty(Object obj) {
        try {
            if (obj == null) {
                return true;
            } else if (obj instanceof String) {
                return ((String) obj).trim().isEmpty();
            } else if (obj instanceof Collection) {
                return ((Collection<?>) obj).isEmpty();
            } else if (obj instanceof Map) {
                return ((Map<?, ?>) obj).isEmpty();
            } else if (obj.getClass().isArray()) {
                return (Array.getLength(obj) == 0);
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            log.error("[IllegalArgumentException] Try/Catch...usingParameters Running : " + e.getMessage());
        } catch (Exception e) {
            log.error("[" + e.getClass() + "] Try/Catch...Exception : " + e.getMessage());
        }

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEqualsStr(Object obj, String s) {
		if (isEmpty(obj)) {
			return false;
		}
        if (s.equals(String.valueOf(obj))) {
            return true;
        }
        return false;
    }

    public static boolean isNotEqualsStr(Object obj, String s) {
        return !isEqualsStr(obj, s);
    }

}
