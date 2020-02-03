package com.cloudtemplate.uaaserver.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.rmi.UnexpectedException;
import java.security.MessageDigest;
import java.util.UUID;

public class Codec {
    public Codec() {
    }

    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    public static String encodeBASE64(String value) throws UnexpectedException {
        try {
            return new String(Base64.encodeBase64(value.getBytes("utf-8")));
        } catch (UnsupportedEncodingException var2) {
            throw new UnexpectedException(var2.getMessage());
        }
    }

    public static String encodeBASE64(byte[] value) {
        return new String(Base64.encodeBase64(value));
    }

    public static byte[] decodeBASE64(String value) throws UnexpectedException {
        try {
            return Base64.decodeBase64(value.getBytes("utf-8"));
        } catch (UnsupportedEncodingException var2) {
            throw new UnexpectedException(var2.getMessage());
        }
    }

    public static String hexMD5(String value) throws UnexpectedException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes("utf-8"));
            byte[] digest = messageDigest.digest();
            return byteToHexString(digest);
        } catch (Exception var3) {
            throw new UnexpectedException(var3.getMessage());
        }
    }

    public static String hexSHA1(String value) throws UnexpectedException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes("utf-8"));
            byte[] digest = md.digest();
            return byteToHexString(digest);
        } catch (Exception var3) {
            throw new UnexpectedException(var3.getMessage());
        }
    }

    public static String byteToHexString(byte[] bytes) {
        return String.valueOf(Hex.encodeHex(bytes));
    }

    public static byte[] hexStringToByte(String hexString) throws UnexpectedException {
        try {
            return Hex.decodeHex(hexString.toCharArray());
        } catch (DecoderException var2) {
            throw new UnexpectedException(var2.getMessage());
        }
    }
}
