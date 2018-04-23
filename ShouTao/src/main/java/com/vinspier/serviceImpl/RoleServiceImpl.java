package com.vinspier.serviceImpl;

import com.vinspier.dao.RoleDao;
import com.vinspier.pojo.Role;
import com.vinspier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role getById(String roleId) throws Exception{
        return roleDao.getById(roleId);
    }
}
