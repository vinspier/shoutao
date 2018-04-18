package com.vinspier.dao;

import com.vinspier.pojo.OrderItem;
import com.vinspier.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Repository
public interface OrderDao {
    void createOrder(Order order) throws Exception;
    void createOrderItem(OrderItem orderItem) throws Exception;
    Order getOrderByOrderID(String orderID) throws Exception;
    List<Order> getOrderByUid(String uid) throws Exception;
    List<Order> getOrderByUidAndState(String uid,Integer state) throws Exception;
    List<OrderItem> getOrderItems(String oid) throws Exception;
    void orderDeleteByOid(String oid) throws Exception;
    void orderItemsDeleteByOid(String oid) throws Exception;
}
