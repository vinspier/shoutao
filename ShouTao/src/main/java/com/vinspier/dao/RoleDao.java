package com.vinspier.dao;

import com.vinspier.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
    Role getById(String roleId) throws Exception;
}
