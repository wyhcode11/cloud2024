package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class orderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;
    @Override
    @GlobalTransactional(name="wyh-service-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //检查xid
        String xid = RootContext.getXID();
        log.info("开始打造订单:  "+"xid: "+xid);
        order.setStatus(0);
        int i = orderMapper.insertSelective(order);
        Order order1=null;
        if(i>0){
            order1=orderMapper.selectOne(order);
            log.info("新建订单成功,order: "+order1);
            System.out.println("--------------");
            log.info("开始扣减库存--------------------");
            storageFeignApi.decrease(order1.getProductId(),order1.getCount());
            log.info("开始扣减账户余额-----------------");
            accountFeignApi.decrease(order1.getUserId(),order1.getMoney());
            order1.setStatus(1);

            Example example = new Example(Order.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",order1.getUserId());
            criteria.andEqualTo("status",0);
            int updateResult = orderMapper.updateByExampleSelective(order1, example);
            log.info("修改订单完成: "+updateResult);
            log.info("orderFromDb: "+order1);
        }else{
            System.out.println();
            log.info("---------------------------");
            log.info("结束打造订单: "+"xid: "+xid);
        }
    }
}
