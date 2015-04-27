package com.github.yoojia.fast.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash加密工具类
 *
 * @author  yoojia.chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
public class HashEncryption {

    /**
     * Hash加密文本内容，并指定Hash算法名称
     * @param input 文本内容
     * @param algorithm Hash算法名称
     * @return 加密后的文本内容。如果算法名称不存在，返回null。
     */
    public static String encrypt(String input, String algorithm) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance(algorithm);
            digest.update(input.getBytes());
            byte bytes[] = digest.digest();
            final StringBuilder buf = new StringBuilder();
            for (byte b : bytes){
                String hex = Integer.toHexString(0xFF & b);
                while (hex.length() < 2) hex = "0" + hex;
                buf.append(hex);
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
