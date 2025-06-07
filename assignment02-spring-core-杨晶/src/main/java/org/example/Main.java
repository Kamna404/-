package org.example;

import org.example.aspect.LoggingAspect;
import org.example.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example") // 扫描包，自动注册 Bean
public class Main {
    public static void main(String[] args) {
        // 初始化 Spring 容器
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // 获取 OrderService Bean
        OrderService orderService = context.getBean(OrderService.class);

        // 使用 AOP 代理
        LoggingAspect loggingAspect = new LoggingAspect(orderService);
        OrderService proxyService = (OrderService) loggingAspect.createProxy();

        // 测试订单创建功能
        proxyService.createOrder("ORD001", "手机");
    }
}
