package com.vinspier.controller;

import com.vinspier.pojo.*;

import com.vinspier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.Iterator;
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
               Order order = orderService.createOrder(idItems,request.getParameter("address"),request.getParameter("contactname"),request.getParameter("telephone"),(User) request.getSession().getAttribute("user"));
                model.addAttribute("order",order);
        } catch(Exception e) {
            request.setAttribute("msg","提交订单失败，请重新提交");
            return "view/notification_message";
        }
        return "view/pay_information";
    }

    @RequestMapping(value = "/createDirectBuyOrder")
    public String createDirectBuyOrder(HttpServletRequest request,Model model) throws Exception{
        String pid = request.getParameter("pid");
        int count = Integer.parseInt(request.getParameter("count"));
        String address = request.getParameter("address");
        String contactname = request.getParameter("contactname");
        String telephone = request.getParameter("telephone");
        Order order = orderService.createDirectBuyOrder(pid,count,address,contactname,telephone,(User)request.getSession().getAttribute("user"));
        model.addAttribute("order",order);
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
        List<Order> orderList = orderService.getOrderByUid(uid);
        Iterator<Order> orderIterator = orderList.iterator();
        int allCounts = orderList.size();
        int unPay = 0;
        int unDelivery = 0;
        int alreadyDelivery = 0;
        int alreadyDone = 0;
        while(orderIterator.hasNext()){
            Order order = orderIterator.next();
            if(order.getState() == 0){unPay++;}
            if(order.getState() == 1){unDelivery++;}
            if(order.getState() == 2){alreadyDelivery++;}
            if(order.getState() == 3){alreadyDone++;}
            if(order.getState() == Integer.parseInt(state)){
                List<OrderItem> orderItems = orderService.getOrderItems(order.getOid());
                for (OrderItem orderItem : orderItems) {
                    orderItem.setProduct(orderItem.getProduct());
                    order.getOrderItems().add(orderItem);
                }
            }else {
                orderIterator.remove();
            }
        }
        model.addAttribute("orders", orderList);
        model.addAttribute("allCounts",allCounts);
        model.addAttribute("unPay",unPay);
        model.addAttribute("unDelivery",unDelivery);
        model.addAttribute("alreadyDelivery",alreadyDelivery);
        model.addAttribute("alreadyDone",alreadyDone);
        return "view/order_list";
    }

    @RequestMapping(value = "/order_list")
    public String orderList(HttpServletRequest request,Model model) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = orderService.getOrderByUid(user.getUid());
        int unPay = 0;
        int unDelivery = 0;
        int alreadyDelivery = 0;
        int alreadyDone = 0;
        for(Order order:orders) {
            if(order.getState() == 0){unPay++;}
            if(order.getState() == 1){unDelivery++;}
            if(order.getState() == 2){alreadyDelivery++;}
            if(order.getState() == 3){alreadyDone++;}
            order.setUser(user);
            List<OrderItem> orderItems = orderService.getOrderItems(order.getOid());
            for (OrderItem orderItem : orderItems) {
                orderItem.setProduct(orderItem.getProduct());
                order.getOrderItems().add(orderItem);
            }
        }
        model.addAttribute("orders", orders);
        model.addAttribute("allCounts",orders.size());
        model.addAttribute("unPay",unPay);
        model.addAttribute("unDelivery",unDelivery);
        model.addAttribute("alreadyDelivery",alreadyDelivery);
        model.addAttribute("alreadyDone",alreadyDone);
        return "view/order_list";
    }

    @RequestMapping(value = "/deleteOrderByOid")
    public String deleteOrderByOid(@RequestParam("oid") String oid) throws Exception{
        orderService.orderDeleteByOid(oid);
        return "redirect:order_list";
    }

    @RequestMapping(value = "/payDone")
    public String payDone(HttpServletRequest request,@RequestParam("oid") String oid) throws Exception{
        User user = (User)request.getSession().getAttribute("user");
        String message =  orderService.orderPayDone(oid,user.getUid());
        request.setAttribute("msg",message);
        return "view/notification_message";
    }
}
