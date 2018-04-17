package com.vinspier.dao;

import com.vinspier.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Repository
public interface UserDao {
    void save(User user) throws Exception;
    User getByCode(String code) throws Exception;
    void active(User user) throws Exception;
    User getByUsernameAndPassword(String username,String password) throws Exception;
    User getUserByUid(String uid) throws Exception;
    void modify(User user) throws Exception;
}
