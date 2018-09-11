/**  
 * Project Name:ali-mysql-myBatis  
 * File Name:MySQLPaginationPlugin.java  
 * Package Name:org.mybatis.generator  
 *
 * Date:2018年5月18日下午6:13:14  
 * Copyright (c) 2018, Accenture All Rights Reserved.  
 *  
 */  
  
package org.mybatis.generator;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**  
 * ClassName:MySQLPaginationPlugin <br/>   
 * Reason:   TODO . <br/>  
 *
 * @author   yunchao.li  
 * Date:     2018年5月18日 下午6:13:14 <br/>  
 * @version  1.0.0 <br/>  
 */
public final class MySQLPaginationPlugin extends PluginAdapter {

    // ------------------------------Page-----------------------------------------------
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addPage(topLevelClass, introspectedTable, "page");
        // 非空判定，异常处理删除
        editNullCondition(topLevelClass, introspectedTable);
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        XmlElement page = new XmlElement("if");
        page.addAttribute(new Attribute("test", "page != null"));
        page.addElement(new TextElement("limit #{page.startRow} , #{page.pageSize}"));
        element.addElement(page);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**  
     * 删除null条件场合的异常处理.<br/>  
     *    
     * @param topLevelClass
     * @param introspectedTable  
     * @author yunchao.li
     */
    private void editNullCondition(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        InnerClass criteria = null;
        // first, find the Criteria inner class
        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) { //$NON-NLS-1$
                criteria = innerClass;
                break;
            }
        }

        if (criteria == null) {
            // can't find the inner class for some reason, bail out.
            return;
        }
        List<Method> paramList = new ArrayList<>();
        for (Method item : criteria.getMethods()) {
            paramList.add(item);
            if (("addCriterion").equals(item.getName())) {
                List<String> tmp = item.getBodyLines();
                for (int i = 0; i < tmp.size(); i++) {
                    String line = tmp.get(i);
                    if (line.contains("RuntimeException")) {
                        tmp.remove(i);
                        tmp.add(i, "return ;");
                    }
                }
            }
        }
        criteria.getMethods().clear();
        criteria.getMethods().addAll(paramList);
    }
    /**
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.ali.sealing.vo.PageVO"));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType("com.ali.sealing.vo.PageVO"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("com.ali.sealing.vo.PageVO"), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("com.ali.sealing.vo.PageVO"));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }
    // ----------------------------------PageEnd------------------------------------------
}
  
