// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi
// Source File Name:   Md5.java

package com.gdie.util;

import java.security.*;

public final class Md5 {

    private Md5() {
    }

    private static String byteHEX(byte ib) {
        char Digit[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'
        };
        char ob[] = new char[2];
        ob[0] = Digit[ib >>> 4 & 0xf];
        ob[1] = Digit[ib & 0xf];
        String s = new String(ob);
        return s;
    }

    public static String encrypt(String source) {
        String s;
        try {
            MessageDigest std = MessageDigest.getInstance("MD5", "SUN");
            byte digest[] = std.digest(source.getBytes());
            String digestHexStr = "";
            for (int i = 0; i < 16; i++) {
                digestHexStr = String.valueOf(digestHexStr) +
                    String.valueOf(byteHEX(digest[i]));

            }
            String s1 = digestHexStr;
            return s1;
        }
        catch (Exception e) {
            s = "";
        }
        return s;
    }
}
