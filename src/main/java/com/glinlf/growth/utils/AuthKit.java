package com.glinlf.growth.utils;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public final class AuthKit {

    // miniapp / c1314c950918AE86  bWluaWFwcDpjMTMxNGM5NTA5MThBRTg2
    // crm-admin / 2dfGK092a6faZT  b21zLWFkbWluOjJkZkdLMDkyYTZmYVpU

    /**
     * 计算 http.header['Authorization']
     *
     * @param user
     * @param pwd
     * @return
     */
    public static String basicAuth(String user, String pwd) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(pwd);
        final String auth = user + ":" + pwd;
        final byte[] ascii = auth.getBytes(Charset.forName("ISO-8859-1"));
        final String str = Base64.getEncoder().encodeToString(ascii);
        final String authHeader = "Basic " + str;
        return authHeader;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(basicAuth("crm-admin", "2dfGK092a6faZT"));
    }
}
