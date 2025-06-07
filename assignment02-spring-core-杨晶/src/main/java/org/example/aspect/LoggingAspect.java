package org.example.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class LoggingAspect implements InvocationHandler {

    private Object target; // 目标对象

    public LoggingAspect(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置通知：在方法执行前记录日志
        System.out.println("日志记录：方法 " + method.getName() + " 开始执行，时间：" + new Date());

        // 执行目标方法
        Object result = method.invoke(target, args);

        // 后置通知：在方法执行后记录日志
        System.out.println("日志记录：方法 " + method.getName() + " 执行结束，时间：" + new Date());
        return result;
    }

    // 创建代理对象
    public Object createProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
}
