package com.insect.mapper;

import com.insect.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    List<Order> searchAllOrder();
    void addOrder(Order order);
    Order queryOrderById(@Param("OrderId") String id);
    void deleteOrderById(@Param("OrderId") String id);
    void updateOrder(Order order);
}
