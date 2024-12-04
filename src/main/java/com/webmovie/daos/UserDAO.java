package com.webmovie.daos;

import com.webmovie.entities.User;

import java.util.List;



public interface UserDAO extends DAOs <User,Integer> {
    List<User> findAll(int firstResult, int maxResults);
    User findByEmail(String email);
    User findValidEmail(User entity);
    List<User> findByEmail(int firstResult, int maxResults, String email, Boolean admin);
    List<User> findByEmail(int firstResult, int maxResults, String email);
    List<User> findByName(String name);
    Long selectCountByName(String search,String admin);
    // check sự tồn tại của user //
    User findByIdOrEmail(String info);
    Boolean checkValidUsername(String username);
}
