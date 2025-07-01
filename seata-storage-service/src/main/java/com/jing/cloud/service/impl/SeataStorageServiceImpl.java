package com.jing.cloud.service.impl;

import com.jing.cloud.mapper.StorageMapper;
import com.jing.cloud.service.SeataStorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SeataStorageServiceImpl implements SeataStorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("--- storageService服务扣减库存开始 ---");
        storageMapper.decrease(productId, count);
        log.info("--- storageService服务扣减库存结束 ---");
    }
}
