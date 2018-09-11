/**
 * Project Name:ali-mysql-myBatis File Name:EncrypRSA.java Package
 * Name:org.mybatis.generator.security
 *
 * Date:2018年4月23日下午9:23:46 Copyright (c) 2018, Accenture All Rights Reserved.
 * 
 */

package org.mybatis.generator.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * ClassName:EncrypRSA <br/>
 * Reason: TODO . <br/>
 *
 * @author yunchao.li Date: 2018年4月23日 下午9:23:46 <br/>
 * @version 1.0.0 <br/>
 */
@SuppressWarnings("restriction")
public class EncrypRSA {
    /**
     * 加密
     * 
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (publicKey != null) {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 解密
     * 
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (privateKey != null) {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        EncrypRSA rsa = new EncrypRSA();
        String msg = "123456";
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(1024);
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        System.out.println("明文是:" + msg);
        // 用公钥加密
        byte[] srcBytes = msg.getBytes();
        byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);

        String pw = HexBin.encode(resultBytes);
//        String pw ="575963036dda41bf3df47366030681addfc5d3d97f4f46484505db726cc65d07de314538294b5151bd2964b5d4c466c52094187bdc1d99aebecca6fb792bc7df05afe971d0cdf085b6a0d935ab3ec985d992fea3549cae47c3cd92c2800c2dd16d42b65a974a3ee4517227f900b9956cac5e67e26d99a1c70c145cee65c3bb7a";
        System.out.println("加密后是:" + pw);
        // 用私钥解密
        byte[] tmp = HexBin.decode(pw);
        byte[] decBytes = rsa.decrypt(privateKey, tmp);

        System.out.println("解密后是:" + new String(decBytes));
    }
}
