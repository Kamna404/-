package org.example.service;

import org.example.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 声明为 Service 类型的 Bean
public class OrderServiceImpl implements OrderService {

    @Autowired // 自动注入 OrderDao
    private OrderDao orderDao;

    @Override
    public void createOrder(String orderId, String productName) {
        System.out.println("创建订单：订单ID=" + orderId + ", 商品名称=" + productName);
        orderDao.saveOrder(orderId, productName);
    }
}
