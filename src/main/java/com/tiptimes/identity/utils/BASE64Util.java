package com.tiptimes.identity.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @Description: BASE64加密工具类
 * @Author user
 * @Date 2019/9/4
 * @Version V1.0
 **/
public class BASE64Util {
    /**
     * BASE64加密
     * @param str
     * @return
     */
    public static String encryptByBASE64(String str){
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /**
     * BASE64解密
     * @param str
     * @return
     */
    public static String getFromBase64(String str) {
        byte[] b;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
