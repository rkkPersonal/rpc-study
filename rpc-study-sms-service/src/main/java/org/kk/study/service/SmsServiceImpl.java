package org.kk.study.service;

import org.kk.rpc.spring.annotation.KkRpcService;

/**
 * @author Steven
 */
@KkRpcService
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean sendSmsMsg(Object msg) {
        System.out.println(msg + "," + "来电转接！！！！！！！！！！");
        return true;
    }
}
