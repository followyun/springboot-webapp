package com.my.webapp.service.impl;

import com.my.webapp.common.constants.ErrorCode;
import com.my.webapp.common.exception.BusinessException;
import com.my.webapp.service.ExceptionService;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class ExceptionServiceImpl implements ExceptionService{
    @Override
    public void businessExceptionTest() throws BusinessException {
        throw new BusinessException(ErrorCode.PSSWORD_ERROR);
    }
}
