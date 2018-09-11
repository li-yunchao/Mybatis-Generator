/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:PatternTest.java  
 * Package Name:org.mybatis.generator  
 *
 * Date:2018年4月25日上午10:40:00  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
 *  
 */  
  
package org.mybatis.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * ClassName:PatternTest <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年4月25日 上午10:40:00 <br/>  
 * @version  1.0.0 <br/>  
 */
public class PatternTest {

    public static void main(String[] args) {
        Pattern p=Pattern.compile("^[\\w\\u2E80-\\u9FFF]+\\([\\w]+\\)$");
        Matcher m=p.matcher("埃雷(aa_12)");
        System.out.print(m.find());
    }

}
  
