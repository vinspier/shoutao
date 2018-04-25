package com.vinspier.serviceImpl;

import com.vinspier.dao.CartDao;
import com.vinspier.dao.OrderDao;
import com.vinspier.dao.ProductDao;
import com.vinspier.dao.UserDao;
import com.vinspier.pojo.*;
import com.vinspier.service.OrderService;
import com.vinspier.utils.UUIDUtils;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public Order createOrder(List<String> itemIds, String address, String contactname, String telephone,User user) throws Exception{
        Order order = new Order();
        order.setOid(UUIDUtils.getId());
        order.setOrdertime(new Date());
        order.setState(Constant.ORDER_UN_PAY);
        order.setAddress(address);
        order.setContactname(contactname);
        order.setTelephone(telephone);


        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        Double total = 0.0;
        for(String s:itemIds){
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getId());
            CartItem cartItem = cartDao.getCartItemById(s);

            total += cartItem.getSubtotal();

            orderItem.setCounts(cartItem.getCounts());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());

            orderItem.setOrder(order);
            orderItems.add(orderItem);
            orderDao.createOrderItem(orderItem);
            cartDao.deleteFromCart(s);
        }
        order.setTotal(total);
        order.setUser(user);
        order.setOrderItems(orderItems);
        orderDao.createOrder(order);

        return order;
    }

    public Order createDirectBuyOrder(String pid,int count,String address, String contactname, String telephone, User user) throws Exception{
        Order order = new Order();
        order.setOid(UUIDUtils.getId());
        order.setOrdertime(new Date());
        order.setState(Constant.ORDER_UN_PAY);
        order.setAddress(address);
        order.setContactname(contactname);
        order.setTelephone(telephone);

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemid(UUIDUtils.getId());
        orderItem.setCounts(count);
        Product product = productDao.getById(pid);
        orderItem.setSubtotal(product.getShop_price()*count);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItems.add(orderItem);

        order.setTotal(product.getShop_price()*count);
        order.setUser(user);
        order.setOrderItems(orderItems);

        orderDao.createOrderItem(orderItem);
        orderDao.createOrder(order);
        return order;
    }

    public Order getOrderByOrderID(String orderID) throws Exception{
        return orderDao.getOrderByOrderID(orderID);
    }

    public List<Order> getOrderByUid(String uid) throws Exception{
        return orderDao.getOrderByUid(uid);
    }

    public List<Order> getOrderByUidAndState(String uid,Integer state) throws Exception{
        return orderDao.getOrderByUidAndState(uid,state);
    }

    public List<OrderItem> getOrderItems(String oid) throws Exception{
        return orderDao.getOrderItems(oid);
    }
   public void orderDeleteByOid(String oid) throws Exception{
        orderDao.orderItemsDeleteByOid(oid);
        orderDao.orderDeleteByOid(oid);
   }

   public String orderPayDone(String oid,String uid) throws Exception{
        Double balance = userDao.getUserByUid(uid).getBalance();
        Double total = orderDao.getOrderByOrderID(oid).getTotal();
        if(balance>0&&balance>total){
            orderDao.orderPayDone(oid,Constant.ORDER_UN_DELIVERY);
            userDao.updateBalance(uid,balance-total);
            return "付款成功！      <a href='/order_list'>查看我的订单</a>";
        }else{
            return "余额不足！请充值后再付款      <a href='/index'>返回首页</a>";
        }
   }
}
