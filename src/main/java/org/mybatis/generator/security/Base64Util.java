/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:Base64Util.java  
 * Package Name:org.mybatis.generator  
 *
 * Date:2018年4月23日下午8:54:32  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
 *  
 */  
  
package org.mybatis.generator.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;


/**  
 * ClassName:Base64Util <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年4月23日 下午8:54:32 <br/>  
 * @version  1.0.0 <br/>  
 */
public class Base64Util {
//    private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String UTF_8 = "UTF-8";

    /**
     * 对给定的字符串进行base64解码操作
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
//            logger.error(inputData, e);
        }

        return null;
    }

    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
//            logger.error(inputData, e);
        }

        return null;
    }

    public static void main(String[] args) {
        String strData = Base64Util.encodeData("123456");
        System.out.println(strData);
        System.out.println(Base64Util.decodeData(strData));
    }
}
  
