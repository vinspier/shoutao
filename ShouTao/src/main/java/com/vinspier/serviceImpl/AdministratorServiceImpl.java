package com.vinspier.serviceImpl;

import com.vinspier.dao.AdminDao;
import com.vinspier.pojo.Administrator;
import com.vinspier.service.AdministratorService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdminDao adminDao;

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
}
