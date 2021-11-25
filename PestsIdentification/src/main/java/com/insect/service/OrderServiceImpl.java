package com.insect.service;

import com.insect.mapper.OrderMapper;
import com.insect.pojo.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> searchAllOrder() {
        return orderMapper.searchAllOrder();
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public Order queryOrderById(String id) {
        return orderMapper.queryOrderById(id);
    }

    @Override
    public void deleteOrderById(String id) {
        orderMapper.deleteOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }
}
