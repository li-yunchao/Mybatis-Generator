/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:EncrypSHA.java  
 * Package Name:org.mybatis.generator.security  
 *
 * Date:2018年4月24日上午8:19:22  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
 *  
 */  
  
package org.mybatis.generator.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**  
 * ClassName:EncrypSHA <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年4月24日 上午8:19:22 <br/>  
 * @version  1.0.0 <br/>  
 */
public class EncrypSHA {
    public byte[] eccrypt(String info) throws NoSuchAlgorithmException{  
        MessageDigest md5 = MessageDigest.getInstance("SHA");  
        byte[] srcBytes = info.getBytes();  
        //使用srcBytes更新摘要  
        md5.update(srcBytes);  
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();  
        return resultBytes;  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchAlgorithmException  
     */  
    public static void main(String[] args) throws NoSuchAlgorithmException {  
        String msg = "123456";  
        EncrypSHA sha = new EncrypSHA();  
        byte[] resultBytes = sha.eccrypt(msg);  
        System.out.println("明文是：" + msg);  
        System.out.println("密文是：" + new String(resultBytes));  
          
    }  
}
  
