package org.kk.study.service;

import org.kk.rpc.spring.annotation.KkRpcReference;
import org.springframework.stereotype.Service;

/**
 * @author Steven
 */
@Service
public class OrderServiceImpl implements OrderService {

    @KkRpcReference
    SmsService smsService;

    @Override
    public boolean createOrder() {
        System.out.println("创建订单成功");
        smsService.sendSmsMsg("发送短信。。。。。。。");
        return true;
    }
}
