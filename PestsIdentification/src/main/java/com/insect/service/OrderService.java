package com.insect.service;

import com.insect.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    List<Order> searchAllOrder();
    void addOrder(Order order);
    Order queryOrderById(String id);
    void deleteOrderById(String id);
    void updateOrder(Order order);
}
