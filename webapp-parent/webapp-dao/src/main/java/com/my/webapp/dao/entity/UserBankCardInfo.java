package com.my.webapp.dao.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author struggling_rong
 * @since 2018-04-12
 */
@TableName("t_wa_user_bank_card_info")
public class UserBankCardInfo extends Model<UserBankCardInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long cusId;
    /**
     * 发卡行识别码
     */
    private String binId;
    /**
     * 发卡行号
     */
    private String bankNumber;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 卡类型(借记卡，贷记卡)
     */
    private Integer cardType;
    /**
     * 银行卡卡号
     */
    private String cardNumber;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 创建时间
     */
    private Date createdTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserBankCardInfo{" +
        ", id=" + id +
        ", cusId=" + cusId +
        ", binId=" + binId +
        ", bankNumber=" + bankNumber +
        ", bankName=" + bankName +
        ", cardType=" + cardType +
        ", cardNumber=" + cardNumber +
        ", updatedTime=" + updatedTime +
        ", createdTime=" + createdTime +
        "}";
    }
}
