package org.example.dao;

import org.springframework.stereotype.Repository;

@Repository // 声明为 Repository 类型的 Bean
public class OrderDaoImpl implements OrderDao {

    @Override
    public void saveOrder(String orderId, String productName) {
        // 模拟保存订单到数据库
        System.out.println("保存订单到数据库：订单ID=" + orderId + ", 商品名称=" + productName);
    }
}
