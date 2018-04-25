package com.vinspier.service;

import com.vinspier.pojo.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator getByNameAndPassword(String name, String password) throws Exception;
    Administrator getByAdminId(int adminId) throws Exception;
    Administrator resetPassword(int adminId,String password) throws Exception;
    List<Administrator> adminList(int id) throws Exception;
    void deleteAdmin(int adminId) throws Exception;
    int addNewAdmin(Administrator admin) throws  Exception;
}
