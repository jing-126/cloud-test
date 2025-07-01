package com.jing.cloud.service.impl;

import com.jing.cloud.apis.AccountFeignApi;
import com.jing.cloud.apis.StorageFeignApi;
import com.jing.cloud.entities.Order;
import com.jing.cloud.mapper.OrderMapper;
import com.jing.cloud.resp.Result;
import com.jing.cloud.service.SeataOrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class SeataOrderServiceImpl implements SeataOrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi; // 库存微服务

    @Resource
    private AccountFeignApi accountFeignApi; // 账户微服务

    @Override
    @GlobalTransactional(name = "cloud-order-transactional", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        // xid 全局事务ID的检查 *重要
        String xid = RootContext.getXID();
        // 新建订单
        log.info("--- 创建订单:xid={} ---\r\n", xid);
        // 订单默认状态为0 创建中
        order.setStatus(0);
        int createResult = orderMapper.insertSelective(order);
        // 重新查询刚插入的订单信息
        Order orderFromDb = null;
        if (createResult > 0) {
            orderFromDb = orderMapper.selectOne(order);
            log.info("--- 新建订单成功, 订单信息{} ---\r\n", orderFromDb);
            // 扣减库存
            log.info("--- 订单微服务调用storage库存开始,进行扣减 ---\r\n");
            storageFeignApi.decrease(orderFromDb.getProductId(), orderFromDb.getCount());
            log.info("--- 订单微服务调用storage库存结束,扣减完成 ---\n");
            // 扣减余额
            log.info("--- 订单微服务调用account账户开始,进行扣减 ---\r\n");
            accountFeignApi.decrease(orderFromDb.getUserId(), orderFromDb.getMoney());
            log.info("--- 订单微服务调用account账户结束,扣减完成 ---\n");
            // 相关服务扣减完成，更新订单服务状态
            orderFromDb.setStatus(1);
            Example whereCondition = new Example(Order.class);
            whereCondition.createCriteria()
                    .andEqualTo("userId", orderFromDb.getUserId())
                    .andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDb, whereCondition);
            log.info("--- 修改订单状态完成 --- {} \r\n", updateResult);
            log.info(" orderFromDb info: {} \r\n", orderFromDb);
        }
        log.info("--- 创建订单结束:xid={} ---\r\n", xid);
    }
}
