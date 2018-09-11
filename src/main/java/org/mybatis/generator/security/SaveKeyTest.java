/**
 * Project Name:ali-mysql-myBatis File Name:SaveKeyTest.java Package
 * Name:org.mybatis.generator.security
 *
 * Date:2018年4月24日上午9:51:09 Copyright (c) 2018, Accenture All Rights Reserved.
 * 
 */

package org.mybatis.generator.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * ClassName:SaveKeyTest <br/>
 * Reason: TODO . <br/>
 *
 * @author yunchao.li Date: 2018年4月24日 上午9:51:09 <br/>
 * @version 1.0.0 <br/>
 */
@SuppressWarnings("restriction")
public class SaveKeyTest {

    // 要加密的数据
    public static String bobo = "http://blog.csdn.net/bobo0915";
    // 加密后的密文数据
    public static byte[] result; // 需要传输给 接收方 接收方进行解密

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        sendSecret();
        receiveSecret();
    }

    /**
     * 模拟发送方 生成秘钥，并保存秘钥 (通过其他方式把秘钥提供给接收方，邮件，网络，U盘...)
     */
    public static void sendSecret() {
        try {
            // 1.初始化key秘钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            // 转换key秘钥
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(secretKey.getEncoded());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Key key = secretKeyFactory.generateSecret(deSedeKeySpec);
            // 2.对生成的密钥key进行编码保存
            String keyencode = HexBin.encode(key.getEncoded());
            // 写入文件保存
            File file = new File("D:\\keyencode.txt");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(keyencode.getBytes());
            outputStream.close();
            System.out.println(keyencode + " -----> key保存成功");

            // 3.进行加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            result = cipher.doFinal(bobo.getBytes());
            System.out.println("发送方进行加密：" + HexBin.encode(result));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 模拟接收方 读取文件中的秘钥，进行加解密
     */
    public static void receiveSecret() {
        try {
            // 1.读取文件中的密钥
            File file = new File("D:\\keyencode.txt");
            InputStream inputStream = new FileInputStream(file);// 文件内容的字节流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 得到文件的字符流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // 放入读取缓冲区
            String readd = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readd = bufferedReader.readLine()) != null) {
                stringBuffer.append(readd);
            }
            inputStream.close();
            String keystr = stringBuffer.toString();
            System.out.println(keystr + " -----> key读取成功"); // 读取出来的key是编码之后的 要进行转码

            // 2.通过读取到的key 获取到key秘钥对象
            byte[] keybyte = HexBin.decode(keystr);
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keybyte);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Key key = secretKeyFactory.generateSecret(deSedeKeySpec); // 获取到key秘钥

            // 3.进行解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("接收方进行解密：" + new String(result));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
