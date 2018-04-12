package com.my.webapp.service;

import com.my.webapp.common.exception.BusinessException;

/**
 */
public interface UserBankCardService {
    void updateBankCardInfo(Long cusId, String bankCardNum) throws BusinessException;

    /**
     * 更新用户银行卡信息任务
     *
     * @param cusId
     * @param bankCardNum
     */
    Long updateCusBankCardInfoTask(Long cusId, String bankCardNum) throws BusinessException;
}
