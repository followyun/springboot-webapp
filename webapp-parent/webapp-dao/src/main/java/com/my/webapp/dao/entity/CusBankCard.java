package com.my.webapp.dao.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户绑定的银行卡，包括借记卡和信用卡（贷款信息表关联当前绑定的信用卡和借记卡）
 * </p>
 *
 * @author struggling_rong
 * @since 2018-04-12
 */
@TableName("t_wa_cus_bank_card")
public class CusBankCard extends Model<CusBankCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 客户id
     */
    private Long customerId;
    /**
     * 开户人姓名
     */
    private String realName;
    /**
     * 开户银行值
     */
    private String bankValue;
    /**
     * 银行卡号
     */
    private String cardNo;
    /**
     * 预留电话号码
     */
    private String bindMobile;
    /**
     * 银行卡类型（1：信用卡；2：借记卡）
     */
    private Integer type;
    /**
     * 是否有效（true：有效；false：无效）
     */
    private Integer activeFlag;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankValue() {
        return bankValue;
    }

    public void setBankValue(String bankValue) {
        this.bankValue = bankValue;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CusBankCard{" +
        ", id=" + id +
        ", customerId=" + customerId +
        ", realName=" + realName +
        ", bankValue=" + bankValue +
        ", cardNo=" + cardNo +
        ", bindMobile=" + bindMobile +
        ", type=" + type +
        ", activeFlag=" + activeFlag +
        ", createdTime=" + createdTime +
        ", updatedTime=" + updatedTime +
        "}";
    }
}
