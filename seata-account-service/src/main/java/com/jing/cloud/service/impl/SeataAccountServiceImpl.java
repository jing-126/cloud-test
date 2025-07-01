package com.jing.cloud.service.impl;

import com.jing.cloud.mapper.AccountMapper;
import com.jing.cloud.service.SeataAccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SeataAccountServiceImpl implements SeataAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("--- accountService服务扣减账户余额开始 ---");
        accountMapper.decrease(userId, money);
//        timeout();
        int errorTest = 10 / 0;
        log.info("--- accountService服务扣减账户余额结束 ---");
    }

    private static void timeout() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
