package com.mxx910.rpc.spring;

import com.mxx910.rpc.Enum.RpcRequestTransportEnum;
import com.mxx910.rpc.annotation.RpcReference;
import com.mxx910.rpc.annotation.RpcService;
import com.mxx910.rpc.config.RpcServiceConfig;
import com.mxx910.rpc.extension.ExtensionLoader;
import com.mxx910.rpc.factory.SingletonFactory;
import com.mxx910.rpc.provider.Impl.ZkServiceProviderImpl;
import com.mxx910.rpc.provider.ServiceProvider;
import com.mxx910.rpc.proxy.RpcClientProxy;
import com.mxx910.rpc.remoting.transport.RpcRequestTransport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;
    private final RpcRequestTransport rpcClient;

    public SpringBeanPostProcessor() {
        this.serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
        this.rpcClient = ExtensionLoader.getExtensionLoader(RpcRequestTransport.class).getExtension(RpcRequestTransportEnum.NETTY.getName());
    }
    // 通过注解注册服务
    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());

            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            // 根据注解创建rpcServiceConfig 并调用注册中心的publishService 将服务注册到注册中心
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();
            serviceProvider.publishService(rpcServiceConfig);
        }
        return bean;
    }
    // 通过注解调用服务端的目标接口
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                        .group(rpcReference.group())
                        .version(rpcReference.version()).build();
                // 创建一个代理对象
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceConfig);
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    // 将代理对象返回 这样一当调用经过@RpcReference对象中的任何方法,都会通过代理对象的invoke方法向服务端发送RPC请求
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }
}

