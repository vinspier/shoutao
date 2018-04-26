package com.vinspier.service;

import com.vinspier.pojo.Order;
import com.vinspier.pojo.OrderItem;
import com.vinspier.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
public interface OrderService {
    Order createOrder(List<String> itemIds, String address, String contactname, String telephone, User user) throws Exception;
    Order createDirectBuyOrder(String pid,int count,String address, String contactname, String telephone, User user) throws Exception;
    Order getOrderByOrderID(String orderID) throws Exception;
    List<Order> getOrderByUid(String uid) throws Exception;
    List<OrderItem> getOrderItems(String oid) throws Exception;
    void orderDeleteByOid(String oid) throws Exception;
    String orderPayDone(String oid,String uid) throws Exception;
    void orderReceivedDone(String oid) throws Exception;
}
