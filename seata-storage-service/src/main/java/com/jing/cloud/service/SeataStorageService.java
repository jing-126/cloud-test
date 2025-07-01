package com.jing.cloud.service;

import org.springframework.stereotype.Service;

@Service
public interface SeataStorageService {
    void decrease(Long productId, Integer count);
}
