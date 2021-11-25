package com.insect.controller;

import com.insect.pojo.Order;
import com.insect.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    @Qualifier("OrderServiceImpl")
    private OrderService orderService;

    @RequestMapping("/allOrder")
    public String allOrder(Model model){
        List<Order> list = orderService.searchAllOrder();
        model.addAttribute("list",list);
        return "allOrder";
    }
    @RequestMapping("/toUpdateOrder")
    public String toUpdateOrder(Model model,String id){
        Order order = orderService.queryOrderById(id);
        model.addAttribute("order",order);
        return "updateOrder";
    }
    @PostMapping("/updateOrder")
    public String updateOrder(Model model,String id,String name){
        System.out.println("id=>"+id);
        System.out.println("name=>"+name);
        orderService.updateOrder(new Order(id,name));
        return "redirect:/allOrder";
    }
}
