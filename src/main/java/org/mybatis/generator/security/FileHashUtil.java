/**
 * Project Name:ali-mysql-myBatis File Name:FileHashUtil.java Package
 * Name:org.mybatis.generator.security
 *
 * Date:2018年4月24日上午8:22:23 Copyright (c) 2018, Accenture All Rights Reserved.
 * 
 */

package org.mybatis.generator.security;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:FileHashUtil <br/>
 * Reason: TODO . <br/>
 *
 * @author yunchao.li Date: 2018年4月24日 上午8:22:23 <br/>
 * @version 1.0.0 <br/>
 */
public class FileHashUtil {
    public static final char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};
    public static final String[] hashTypes = new String[]{"MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512"};

    public void MD5File(String fileName) throws Exception {
        // String fileName = args[0];
        System.out.println("需要获取hash的文件为：　" + fileName);
        List<MessageDigest> mds = new ArrayList<MessageDigest>();
        for (String hashType : hashTypes) {
            MessageDigest md = MessageDigest.getInstance(hashType);
            mds.add(md);
        }
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                for (MessageDigest md : mds) {
                    md.update(buffer, 0, numRead);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        for (MessageDigest md : mds) {
            System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
        }
    }

    public static void main(String[] args) throws Exception {
        String[] fileName = new String[]{"D:/HttpDump.log"};
        FileHashUtil files = new FileHashUtil();
        for (int i = 0; i < fileName.length; i++) {
            files.MD5File(fileName[i]);
        }

    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}
