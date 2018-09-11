/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:EncrypDES3.java  
 * Package Name:org.mybatis.generator.security  
 *
 * Date:2018年4月23日下午9:22:02  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
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
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;  
/**  
 * ClassName:EncrypDES3 <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年4月23日 下午9:22:02 <br/>  
 * @version  1.0.0 <br/>  
 */
@SuppressWarnings("restriction")
public class EncrypDES3_SaveKey {
    // KeyGenerator 提供对称密钥生成器的功能，支持各种算法  
//    private KeyGenerator keygen;  
    // SecretKey 负责保存对称密钥  
//    private SecretKey deskey;  
    // Cipher负责完成加密或解密工作  
//    private Cipher c;  
    // 该字节数组负责保存加密的结果  
    private byte[] cipherByte;  
  
    public EncrypDES3_SaveKey() throws NoSuchAlgorithmException, NoSuchPaddingException {  
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
        KeyGenerator keygen = KeyGenerator.getInstance("DESede");  
        // 生成密钥  
        SecretKey deskey = keygen.generateKey();
        String keyencode= HexBin.encode(deskey.getEncoded());  
        File file=new File("D:\\keyencode.txt");  
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(keyencode.getBytes());
            outputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(deskey+" -----> key保存成功");  
        // 生成Cipher对象,指定其支持的DES算法  
//        c = Cipher.getInstance("DESede");  
    }  
  
    /**  
     * 对字符串加密  
     *   
     * @param str  
     * @return  
     * @throws InvalidKeyException  
     * @throws IllegalBlockSizeException  
     * @throws BadPaddingException  
     */  
    public byte[] Encrytor(String str) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        try {
            // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
            File file = new File("D:\\keyencode.txt");
            InputStream inputStream = new FileInputStream(file);//文件内容的字节流  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //得到文件的字符流  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //放入读取缓冲区  
            String readd = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readd = bufferedReader.readLine()) != null) {
                stringBuffer.append(readd);
            }
            inputStream.close();
            String keystr=stringBuffer.toString();  
            System.out.println(keystr+" -----> key读取成功");
            byte[] keybyte= HexBin.decode(keystr);  
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keybyte);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Key key = secretKeyFactory.generateSecret(deSedeKeySpec); // 获取到key秘钥
            Cipher cipher = Cipher.getInstance("DESede");  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            // 加密，结果保存进cipherByte  
            byte[] src = str.getBytes();  
            cipherByte = cipher.doFinal(src);  
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cipherByte;  
    }  
  
    /**  
     * 对字符串解密  
     *   
     * @param buff  
     * @return  
     * @throws InvalidKeyException  
     * @throws IllegalBlockSizeException  
     * @throws BadPaddingException  
     */  
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  

        try {
            // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
            File file = new File("D:\\keyencode.txt");
            InputStream inputStream = new FileInputStream(file);//文件内容的字节流  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //得到文件的字符流  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //放入读取缓冲区  
            String readd = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readd = bufferedReader.readLine()) != null) {
                stringBuffer.append(readd);
            }
            inputStream.close();
            String keystr = stringBuffer.toString();
            System.out.println(keystr + " -----> key读取成功");
            byte[] keybyte = HexBin.decode(keystr);
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keybyte);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Key key = secretKeyFactory.generateSecret(deSedeKeySpec); // 获取到key秘钥
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, key);
            cipherByte = cipher.doFinal(buff);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cipherByte;  
    }  
  
    /**  
     * @param args  
     * @throws NoSuchPaddingException   
     * @throws NoSuchAlgorithmException   
     * @throws BadPaddingException   
     * @throws IllegalBlockSizeException   
     * @throws InvalidKeyException   
     */  
    public static void main(String[] args) throws Exception {  
        EncrypDES3_SaveKey des = new EncrypDES3_SaveKey();  
        String msg ="Pr0d1234";  
        byte[] encontent = des.Encrytor(msg);  
        String pw = HexBin.encode(encontent);
        byte[] tmp = HexBin.decode(pw);
        byte[] decontent = des.Decryptor(tmp);  
        System.out.println("明文是:" + msg);  
        System.out.println("加密后:" + pw);  
        System.out.println("解密后:" + new String(decontent));  
  
    }  
}
  
