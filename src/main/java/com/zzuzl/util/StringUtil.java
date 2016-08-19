package com.zzuzl.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Logger logger = LogManager.getLogger(StringUtil.class);

    public static boolean isSchoolNum(String s) {
        return !isEmpty(s) && isPattern(s, "\\d{11}");
    }

    public static boolean isPattern(String s, String pattern, int start) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);
        return matcher.find(start);
    }

    public static boolean isPattern(String s, String pattern) {
        return isPattern(s, pattern, 0);
    }

    public static boolean isEmpty(String s) {
        return StringUtils.isEmpty(StringUtils.trimAllWhitespace(s));
    }

    public static void main(String[] args) {
        logger.info(isSchoolNum("2013341013a"));
    }
}
