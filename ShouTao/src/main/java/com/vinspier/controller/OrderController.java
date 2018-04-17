package com.vinspier.controller;

import com.vinspier.pojo.*;

import com.vinspier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Controller("orderController")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/createOrder")
    public String createOrder(HttpServletRequest request,@RequestParam("orderItemId") List<String> idItems,Model model) throws Exception{
        try {

            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                return "redirect:/login";
            }else {

               Order order = orderService.createOrder(idItems,request.getParameter("address"),request.getParameter("contactname"),request.getParameter("telephone"),user);
                model.addAttribute("order",order);
            }

        } catch(Exception e) {
            request.setAttribute("msg","提交订单失败，请重新提交");
            return "view/msg";
        }
        return "view/pay_information";
    }

    @RequestMapping(value = "/getOrderById")
    public String orderInformation(HttpServletRequest request,Model model) throws Exception{
        Order order = orderService.getOrderByOrderID(request.getParameter("oid"));

        List<OrderItem> orderItems = orderService.getOrderItems(order.getOid());
        for (OrderItem orderItem : orderItems) {
            orderItem.setProduct(orderItem.getProduct());
            order.getOrderItems().add(orderItem);
        }
        model.addAttribute("order",order);

        return "view/pay_information";
    }

    @RequestMapping(value = "/getOrdersByState")
    public String getOrdersByState(@RequestParam("uid") String uid,@RequestParam("state") String state,Model model) throws Exception{
        List<Order> orders = orderService.getOrderByUidAndState(uid,Integer.parseInt(state));
        for(Order order:orders) {
            List<OrderItem> orderItems = orderService.getOrderItems(order.getOid());
            for (OrderItem orderItem : orderItems) {
                orderItem.setProduct(orderItem.getProduct());
                order.getOrderItems().add(orderItem);
            }
        }
        model.addAttribute("orders", orders);
        return "view/order_list";
    }

    @RequestMapping(value = "/order_list")
    public String orderList(HttpServletRequest request,Model model) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = orderService.getOrderByUid(user.getUid());
        for(Order order:orders) {
            order.setUser(user);
            List<OrderItem> orderItems = orderService.getOrderItems(order.getOid());
            for (OrderItem orderItem : orderItems) {
                orderItem.setProduct(orderItem.getProduct());
                order.getOrderItems().add(orderItem);
            }
        }
        model.addAttribute("orders", orders);
        return "view/order_list";
    }
}
