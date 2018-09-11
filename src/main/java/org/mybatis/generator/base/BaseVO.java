/**
 * Project Name:ali-mysql-myBatis File Name:BaseVO.java Package Name:org.mybatis.generator.base
 *
 * Date:2018年4月13日上午9:20:44 Copyright (c) 2018, Accenture All Rights Reserved.
 * 
 */

package org.mybatis.generator.base;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName:BaseVO <br/>
 * Reason: 所有实体类的父类。可将公共的属性所有类序列化集中在此类中 . <br/>
 *
 * @author yunchao.li Date: 2018年4月13日 上午9:20:44 <br/>
 * @version 1.0.0 <br/>
 */
@ApiModel(value="基础对象")
public abstract class BaseVO implements Serializable {

    /**
     * serialVersionUID:.
     */
    private static final long serialVersionUID = -3187555200196087649L;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    /**
     * 有效标识
     */
    @ApiModelProperty(value="有效标识",name="activeFlag", example="Y")
    private String activeFlag;

    /**
     * 删除标识
     */
    @ApiModelProperty(value="删除标识",name="deleteFlag", example="N")
    private String deleteFlag;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",name="createUser")
    private Integer createUser;

    /**
     * 创建日期
     */
    @ApiModelProperty(value="创建日期",name="createDate")
    private Date createDate;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人",name="updateUser")
    private Integer updateUser;

    /**
     * 更新日期
     */
    @ApiModelProperty(value="更新日期",name="updateDate")
    private Date updateDate;

    /**
     * 
     */
    private String appName;

    /**
     * 备注
     * 
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 有效标识
     * 
     * @return active_flag 有效标识
     */
    public String getActiveFlag() {
        return activeFlag;
    }

    /**
     * 有效标识
     * 
     * @param activeFlag 有效标识
     */
    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag == null ? null : activeFlag.trim();
    }

    /**
     * 删除标识
     * 
     * @return delete_flag 删除标识
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 删除标识
     * 
     * @param deleteFlag 删除标识
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    /**
     * 创建人
     * 
     * @return create_user 创建人
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * 
     * @param createUser 创建人
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建日期
     * 
     * @return create_date 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期
     * 
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新人
     * 
     * @return update_user 更新人
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * 
     * @param updateUser 更新人
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 更新日期
     * 
     * @return update_date 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新日期
     * 
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 
     * @return app_name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }


    /**
     *
     * @mbg.generated 2018-04-13
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseVO other = (BaseVO) that;
        return (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getActiveFlag() == null ? other.getActiveFlag() == null : this.getActiveFlag().equals(other.getActiveFlag()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()));
    }

    /**
     *
     * @mbg.generated 2018-04-13
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getActiveFlag() == null) ? 0 : getActiveFlag().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        return result;
    }

    /**
     *
     * @mbg.generated 2018-04-13
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", remark=").append(remark);
        sb.append(", activeFlag=").append(activeFlag);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", appName=").append(appName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
