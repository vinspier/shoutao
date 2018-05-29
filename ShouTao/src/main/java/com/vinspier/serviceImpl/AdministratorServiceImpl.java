package com.vinspier.serviceImpl;

import com.vinspier.dao.AdminDao;
import com.vinspier.dao.OrderDao;
import com.vinspier.dao.UserDao;
import com.vinspier.pojo.*;
import com.vinspier.service.AdministratorService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    public Administrator getByNameAndPassword(String name, String password) throws Exception{
     return adminDao.getByNameAndPassword(name,password);
    }
    public Administrator getByAdminId(int id) throws Exception{
        return adminDao.getByAdminId(id);
    }
    public Administrator resetPassword(int adminId,String password) throws Exception{
        adminDao.resetPassword(adminId,password);
        return adminDao.getByAdminId(adminId);
    }
    public List<Administrator> adminList(int id) throws Exception{
        return adminDao.adminList(id);
    }
    public void deleteAdmin(int adminId) throws Exception{
        adminDao.deleteAdmin(adminId);}
    public int addNewAdmin(Administrator admin) throws  Exception{
        int exist = 0;
        List<Administrator> administrators = adminDao.adminAll();
        for(Administrator ad:administrators){
            if(ad.getAdminName().equals(admin.getAdminName())){
                exist = 1;
            }
        }
        if(exist == 0){
            admin.setRoleId(Constant.ROLE_CHARACTER_ADMIN);
            adminDao.addNewAdmin(admin);
        }
        return exist;
    }

    public List<Order> getOrderByState(int state) throws Exception{
        List<Order> orderList = orderDao.getOrderByState(state);
        for(Order order:orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItems(order.getOid());
            order.setOrderItems(orderItemList);
        }
        return orderList;
    }
    public List<Order> getOrderAllState() throws Exception{
        List<Order> orderList = orderDao.getOrderAllState();
        for(Order order:orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItems(order.getOid());
            order.setOrderItems(orderItemList);
        }
        return orderList;
    }

    public Order getOrderByOid(String uid,String oid) throws Exception{
        Order order = orderDao.getOrderByOrderID(oid);
        List<OrderItem> orderItems = orderDao.getOrderItems(order.getOid());
        User user = userDao.getUserByUid(uid);
        order.setOrderItems(orderItems);
        order.setUser(user);
        return order;
    }

    public void deliveryOrder(String oid,String deliveryNumber) throws Exception{
        orderDao.deliveryOrder(oid,deliveryNumber,Constant.ORDER_ALREADY_DELIVERY);
    }

    public List<Suggestion> findAllSuggestion() throws Exception{
        return adminDao.findAllSuggestion();
    }

    public void deleteSuggestionById(String id) throws Exception{
         adminDao.deleteSuggestionById(id);
    }
}
