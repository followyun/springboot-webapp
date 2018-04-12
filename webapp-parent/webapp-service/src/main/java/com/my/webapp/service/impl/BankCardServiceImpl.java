package com.my.webapp.service.impl;

import com.my.common.util.HttpClientUtil;
import com.my.common.util.JsonUtil;
import com.my.webapp.service.BankCardService;
import com.my.webapp.service.dto.BankCardInfoDTO;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class BankCardServiceImpl implements BankCardService {
    public static final String QUERY_BANK_CARD_INFO_URL = "xxxxxxxx";

    @Override
    public BankCardInfoDTO queryBankCardInfo(String bankCardNum) {
        String responseStr = HttpClientUtil.doPost(QUERY_BANK_CARD_INFO_URL, bankCardNum);
        return JsonUtil.jsonToObj(responseStr, BankCardInfoDTO.class);
    }
}
