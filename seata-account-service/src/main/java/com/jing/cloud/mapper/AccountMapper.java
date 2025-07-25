package com.jing.cloud.mapper;

import com.jing.cloud.entities.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}