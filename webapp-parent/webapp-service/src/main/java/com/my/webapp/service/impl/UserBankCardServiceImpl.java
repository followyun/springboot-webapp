package com.my.webapp.service.impl;

import com.my.webapp.common.constants.ErrorCode;
import com.my.webapp.common.exception.BusinessException;
import com.my.webapp.dao.entity.UserBankCardInfo;
import com.my.webapp.service.BankCardService;
import com.my.webapp.service.UserBankCardService;
import com.my.webapp.service.dto.BankCardInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 */
@Service
public class UserBankCardServiceImpl implements UserBankCardService {
    private static Logger logger = LoggerFactory.getLogger(UserBankCardServiceImpl.class);
    @Autowired
    private BankCardService bankCardService;

    @Override
    public void updateBankCardInfo(Long cusId, String bankCardNum) throws BusinessException {

        logger.debug("cusID{}开始查询银行卡信息。", cusId);
        BankCardInfoDTO bankCardInfoDTO = bankCardService.queryBankCardInfo(bankCardNum);
        if (bankCardInfoDTO == null) {
            logger.error("查询银行卡信息失败");
            throw new BusinessException(ErrorCode.SYSTEM_ERROR.formatMsg("查询银行卡信息失败！", "返回数据BankCardInfoDTO为空"));
        }
        UserBankCardInfo userBankCardInfo = new UserBankCardInfo();
        userBankCardInfo.setCusId(cusId);
        userBankCardInfo.setBinId(bankCardInfoDTO.getBinID());
        userBankCardInfo.setBankNumber(bankCardInfoDTO.getCardNumber());
        userBankCardInfo.setBankName(bankCardInfoDTO.getCardIssuingBankName());
        int cardType = -1;//默认类型
        if (BankCardInfoDTO.Card_Type_DebitCard.equals(bankCardInfoDTO.getIsCreditCard())) {
            cardType = 1;
        } else if (BankCardInfoDTO.Card_Type_CreditCard.equals(bankCardInfoDTO.getIsCreditCard())) {
            cardType = 2;
        }
        userBankCardInfo.setCardType(cardType);
        userBankCardInfo.setCardNumber(bankCardInfoDTO.getCardID());
        userBankCardInfo.setUpdatedTime(new Date());
        userBankCardInfo.setCreatedTime(new Date());

        userBankCardInfo.insert();
        logger.debug("成功更新cusId{}银行信息。", cusId);
    }

    @Override
    public Long updateCusBankCardInfoTask(Long cusId, String bankCardNum) throws BusinessException {
        updateBankCardInfo(cusId, bankCardNum);
        return cusId;
    }

}
