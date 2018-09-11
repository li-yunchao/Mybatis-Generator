/**
 * Project Name:sealing.core.utils File Name:PasswordUtil.java Package
 * Name:com.ali.sealing.core.utils.util
 *
 * Date:2018年4月24日上午9:33:39 Copyright (c) 2018, Accenture All Rights Reserved.
 * 
 */

package org.mybatis.generator.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * ClassName:PasswordUtil <br/>
 * Reason: 加密解密共通 . <br/>
 *
 * @author yunchao.li Date: 2018年4月24日 上午9:33:39 <br/>
 * @version 1.0.0 <br/>
 */
@SuppressWarnings("restriction")
public class PasswordUtil {

    /**
     * strKey:密钥. 
     */
    private static String strKey = "5BA72A31D6BF3289EC38D68FE6D09229ABEA4F37733B0E29";

    /**
     * cipher:Cipher负责完成加密或解密工作.
     */
    private static Cipher cipher;

    /**
     * key:秘钥.
     */
    private static Key key;

    /**
     * 初期化：Key和Cipher.<br/>
     * 
     * @author yunchao.li
     */
    private static void init()
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        byte[] keybyte = HexBin.decode(strKey);
        DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keybyte);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        // 获取到key秘钥
        key = secretKeyFactory.generateSecret(deSedeKeySpec); 
        cipher = Cipher.getInstance("DESede");
    }

    /**
     * 对字符串加密.<br/>
     * 
     * @param password 需要加密字符串
     * @return 加密后结果
     * @author yunchao.li
     */
    public static String Encrytor(String password) {
        byte[] cipherByte = {};
        try {
            init();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] src = password.getBytes();
            cipherByte = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HexBin.encode(cipherByte);
    }

    /**
     * 对字符串解密 .<br/>
     * 
     * @param password
     * @return
     */
    public static String Decryptor(String password) {
        byte[] rstByte = {};
        try {
            init();
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] src = HexBin.decode(password);
            rstByte = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(rstByte);
    }

    public static void main(String[] args) throws Exception {
        String password = "Pr0d1234";
        System.out.println("密码原文   ：" + password);
        System.out.println("密钥          ：" + strKey);
        String rst = PasswordUtil.Encrytor(password);
        System.out.println("加密后结果：" + rst);
        System.out.println("解密后结果：" + PasswordUtil.Decryptor(rst));
    }
}
