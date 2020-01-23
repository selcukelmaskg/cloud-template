package com.poc.shared.util;

import java.util.Random;

public class IpAddressUtil {
    public static String generateIp() {
        StringBuilder ip = new StringBuilder("xx.xx.xx.xx");
        for (int i=0 ; i <11;i++){
            if (i != 2 && i != 5 && i != 8) {
                Random r = new Random();
                int random = r.nextInt((9 - 1) + 1) + 1;
                char c = (char)(random+'0');
                ip.setCharAt(i, c);
            }
        }

        return ip.toString();
    }
}
