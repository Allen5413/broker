package com.allen.util;


import java.security.MessageDigest;

/**
 * Created by Allen on 2015/5/5.
 */
public class MD5Util {
    // MD5加码。32位
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println("MD5:  "+e.getMessage());
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 调用至善接口时专用
     * @param text
     * @return
     */
    public static String getAttopMd5(String text) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte[] s = md.digest();
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
                        .substring(6);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**

     　　* 加密解密算法 执行一次加密，两次解密

     　　*/
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();

        for (int i = 0; i < a.length; i++){

            a[i] = (char) (a[i] ^ 't');

        }
        String s = new String(a);
        return s;
    }
}
