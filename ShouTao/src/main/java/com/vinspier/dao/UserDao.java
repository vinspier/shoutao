package com.vinspier.dao;

import com.vinspier.pojo.Suggestion;
import com.vinspier.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Repository
public interface UserDao {
    void save(User user) throws Exception;
    User getByCode(String code) throws Exception;
    void active(User user) throws Exception;
    void activeByUid(String uid,int state) throws Exception;
    User getByUsernameAndPassword(String username,String password) throws Exception;
    User getUserByUid(String uid) throws Exception;
    void modify(User user) throws Exception;
    void modifyPassword(String uid,String password) throws Exception;
    void updateBalance(String uid,double balance) throws Exception;
    List<User> getAllUsers() throws Exception;
    List<User> getAllUsersNotActive(int state) throws Exception;
    List<User> getAllUsersActive(int state) throws Exception;
    ArrayList<String> getAllUserName()throws Exception;
    void deleteUserByUid(String uid) throws Exception;
    void suggest(Suggestion suggestion) throws Exception;
    List<Suggestion> findAllSuggestion() throws Exception;
    void thumbsUp(Suggestion suggestion) throws Exception;
    Suggestion getSuggestionById(String id) throws Exception;
}
