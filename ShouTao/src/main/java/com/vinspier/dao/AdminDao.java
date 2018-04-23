package com.vinspier.dao;

import com.vinspier.pojo.Administrator;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Administrator getByNameAndPassword(String name,String password) throws Exception;
}
