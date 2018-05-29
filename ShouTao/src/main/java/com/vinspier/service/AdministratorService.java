package com.vinspier.service;

import com.vinspier.pojo.Administrator;
import com.vinspier.pojo.Order;
import com.vinspier.pojo.Suggestion;

import java.util.List;

public interface AdministratorService {
    Administrator getByNameAndPassword(String name, String password) throws Exception;
    Administrator getByAdminId(int adminId) throws Exception;
    Administrator resetPassword(int adminId,String password) throws Exception;
    List<Administrator> adminList(int id) throws Exception;
    void deleteAdmin(int adminId) throws Exception;
    int addNewAdmin(Administrator admin) throws  Exception;
    List<Order> getOrderByState(int state) throws Exception;
    List<Order> getOrderAllState() throws Exception;
    Order getOrderByOid(String uid,String oid) throws Exception;
    void deliveryOrder(String oid,String deliveryNumber) throws Exception;
    List<Suggestion> findAllSuggestion() throws Exception;
    void deleteSuggestionById(String id) throws Exception;
}
