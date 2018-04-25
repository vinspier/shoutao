package com.vinspier.dao;

import com.vinspier.pojo.Administrator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    Administrator getByNameAndPassword(String name,String password) throws Exception;
    Administrator getByAdminId(int id) throws Exception;
    void resetPassword(int adminId,String password) throws Exception;
    List<Administrator> adminList(int id) throws Exception;
    List<Administrator> adminAll() throws Exception;
    void deleteAdmin(int adminId) throws Exception;
    void addNewAdmin(Administrator admin) throws  Exception;
}
