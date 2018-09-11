/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:EncrypMD5.java  
 * Package Name:org.mybatis.generator.security  
 *
 * Date:2018年4月23日下午9:24:47  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
 *  
 */  
  
package org.mybatis.generator.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**  
 * ClassName:EncrypMD5 <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年4月23日 下午9:24:47 <br/>  
 * @version  1.0.0 <br/>  
 */
@SuppressWarnings("restriction")
public class EncrypMD5 {
    public byte[] eccrypt(String info) throws NoSuchAlgorithmException{  
        //根据MD5算法生成MessageDigest对象  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        byte[] srcBytes = info.getBytes();  
        //使用srcBytes更新摘要  
        md5.update(srcBytes);  
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();  
        return resultBytes;  
    }  
      

    // 可逆的加密算法
    public static String reversibleEncryption(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException{  
        String password = "123456";  
        EncrypMD5 md5 = new EncrypMD5();  
        byte[] resultBytes = md5.eccrypt(password);  
        String rst = HexBin.encode(resultBytes);
        System.out.println("原始：" + password);
        System.out.println("MD5后：" + rst);
        System.out.println("MD5后再加密：" + reversibleEncryption(rst));
        System.out.println("解密为MD5后的：" + reversibleEncryption(reversibleEncryption(rst)));
        
    }  
}
  
