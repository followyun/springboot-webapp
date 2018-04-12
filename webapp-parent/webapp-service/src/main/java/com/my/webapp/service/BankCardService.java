package com.my.webapp.service;

import com.my.webapp.service.dto.BankCardInfoDTO;

/**
 */
public interface BankCardService {
    BankCardInfoDTO queryBankCardInfo(String bankCardNum);
}
