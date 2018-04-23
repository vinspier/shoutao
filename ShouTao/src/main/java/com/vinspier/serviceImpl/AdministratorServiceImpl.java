package com.vinspier.serviceImpl;

import com.vinspier.dao.AdminDao;
import com.vinspier.pojo.Administrator;
import com.vinspier.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdminDao adminDao;
   public Administrator getByNameAndPassword(String name, String password) throws Exception{
     return adminDao.getByNameAndPassword(name,password);
    }
}
