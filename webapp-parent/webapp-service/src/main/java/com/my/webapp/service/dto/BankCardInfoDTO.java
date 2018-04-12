package com.my.webapp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public class BankCardInfoDTO {
    public static final String Card_Type_DebitCard = "借记卡";
    public static final String Card_Type_CreditCard = "贷记卡";

    public static final String ERROR_INFO_NO_BANK_INFO = "未匹配到对应银行信息";//"该银行卡号未匹配到对应银行信息"
    /**
     * 是否成功
     */
    @JsonProperty("IsSuccess")
    private boolean isSuccess;

    /**
     * 错误信息
     */
    @JsonProperty("ErrorInfo")
    private String errorInfo;
    /**
     * 卡号
     */
    @JsonProperty("CardID")
    private String cardID;

    /**
     * 发卡行号
     */
    @JsonProperty("CardNumber")
    private String cardNumber;
    /**
     * 银行名称
     */
    @JsonProperty("CardIssuingBankName")
    private String cardIssuingBankName;

    /**
     * 卡类型(借记卡，贷记卡)
     */
    @JsonProperty("IsCreditCard")
    private String isCreditCard;

    /**
     * 发卡行识别码
     */
    @JsonProperty("BinID")
    private String binID;

    /**
     * 识别码长度
     */
    @JsonProperty("BinLen")
    private Integer binLen;

    /**
     * 卡号长度
     */
    @JsonProperty("CardLen")
    private Integer cardLen;

    public static String getCard_Type_DebitCard() {
        return Card_Type_DebitCard;
    }

    public static String getErrorInfoNoBankInfo() {
        return ERROR_INFO_NO_BANK_INFO;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardIssuingBankName() {
        return cardIssuingBankName;
    }

    public void setCardIssuingBankName(String cardIssuingBankName) {
        this.cardIssuingBankName = cardIssuingBankName;
    }

    public String getIsCreditCard() {
        return isCreditCard;
    }

    public void setIsCreditCard(String isCreditCard) {
        this.isCreditCard = isCreditCard;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public Integer getBinLen() {
        return binLen;
    }

    public void setBinLen(Integer binLen) {
        this.binLen = binLen;
    }

    public Integer getCardLen() {
        return cardLen;
    }

    public void setCardLen(Integer cardLen) {
        this.cardLen = cardLen;
    }
}
