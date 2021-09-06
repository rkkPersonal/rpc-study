package org.kk.rpc.utils;

import org.kk.rpc.registry.RegistryService;
import org.kk.rpc.rpc.Invoker;
import org.kk.rpc.rpc.protocol.Protocol;
import org.kk.rpc.rpc.proxy.ProxyFactory;
import org.kk.rpc.spring.config.ProtocolConfig;
import org.kk.rpc.spring.config.RegistryConfig;
import org.kk.rpc.spring.config.ServiceConfig;

import java.net.NetworkInterface;
import java.net.URI;

/**
 * @author Steven
 */
public class KkRpcBootStrap {

    // 暴露service服务
    public static void export(ServiceConfig serviceConfig) {
        // 1. 代理对象
        Invoker invoker = ProxyFactory.getInvoker(serviceConfig.getReference(), serviceConfig.getService());
        try {
            // invoker对象
            // 2根据服务定义的协议，依次暴露。 如果有多个协议那就暴露多次
            for (ProtocolConfig protocolConfig : serviceConfig.getProtocolConfigs()) {
                // 2.1 组织URL --- 协议://ip:端口/service全类名?配置项=值&配置型2=值...
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(protocolConfig.getName() + "://");
                // 此处可选择具体网卡设备 -
                String hostAddress = NetworkInterface.getNetworkInterfaces().
                        nextElement().getInterfaceAddresses().get(0).getAddress().getHostAddress();
                stringBuilder.append(hostAddress + ":");
                stringBuilder.append(protocolConfig.getPort() + "/");
                stringBuilder.append(serviceConfig.getService().getName() + "?");
                // ....版本号啥的的不写了，意思一下吧
                stringBuilder.append("transporter=" + protocolConfig.getTransporter());
                stringBuilder.append("&serialization=" + protocolConfig.getSerialization());

                URI exportUri = new URI(stringBuilder.toString());
                System.out.println("准备暴露服务：" + exportUri);

                // 2.2 创建服务 -- 多个service 用同一个端口 TODO 思考点：一个系统，多个service需要暴露
                Protocol protocol = SpiUtils.getServiceImpl(protocolConfig.getName(), Protocol.class);
                protocol.export(exportUri, invoker);

                // 注册到中心
                for (RegistryConfig registryConfig : serviceConfig.getRegistryConfigs()) {
                    URI registryUri = new URI(registryConfig.getAddress());
                    RegistryService registryService = SpiUtils.getServiceImpl(registryUri.getScheme(), RegistryService.class);
                    registryService.init(registryUri);
                    registryService.register(exportUri);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
