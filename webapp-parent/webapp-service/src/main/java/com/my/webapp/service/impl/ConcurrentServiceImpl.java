package com.my.webapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.my.webapp.common.concurrent.WebAppExecutorService;
import com.my.webapp.dao.entity.CusBankCard;
import com.my.webapp.dao.mapper.CusBankCardMapper;
import com.my.webapp.service.ConcurrentService;
import com.my.webapp.service.UserBankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 */
@Service
public class ConcurrentServiceImpl implements ConcurrentService {
    @Autowired
    private CusBankCardMapper cusBankCardMapper;
    @Autowired
    private UserBankCardService userBankCardService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public void updateAllCusBankCardInfoTask() {

        EntityWrapper<CusBankCard> entityWrapper = new EntityWrapper<>();
        entityWrapper.between("id", 1, 1000);
        List<CusBankCard> cusBankCards = cusBankCardMapper.selectList(entityWrapper);
        WebAppExecutorService executor = WebAppExecutorService.newMyExecutorService(6);
        List<Long> cusIds = new ArrayList<>();
        List<Long> synchronizedList = Collections.synchronizedList(cusIds);
        for (CusBankCard c :
                cusBankCards) {
            try {
                executor.submit(new CusBankCardInfoTask(c.getCustomerId(), c.getCardNo(), synchronizedList));
            } catch (Exception e) {
                logger.debug("更新cusID：{}失败！", c.getCustomerId(), e);
            }
        }

        try {
            executor.shotdown();
            logger.debug("线程池关闭成功！");
        } catch (InterruptedException e) {
            logger.error("线程池关闭发生异常！");
            e.printStackTrace();
        }
        for (Long id :
                synchronizedList) {
            logger.debug("已更新的用户ID:{}", id);

        }
    }

    private class CusBankCardInfoTask implements Callable<Long> {
        private Long cusId;
        private String bankCardNum;
        private List<Long> updatedCusIds;

        @Override
        public Long call() throws Exception {
            userBankCardService.updateBankCardInfo(cusId, bankCardNum);
            updatedCusIds.add(cusId);
            return cusId;
        }

        public CusBankCardInfoTask(Long cusId, String bankCardNum, List<Long> updatedCusIds) {
            this.cusId = cusId;
            this.bankCardNum = bankCardNum;
            this.updatedCusIds = updatedCusIds;
        }
    }

}
