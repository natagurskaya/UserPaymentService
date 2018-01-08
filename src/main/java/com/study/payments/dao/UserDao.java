package com.study.payments.dao;

import com.study.payments.entity.User;

import java.util.List;

public interface UserDao {


    List<User> getAll();

    void save(User user);

    void update(User user);

    void delete(User user);

    User getById(Integer id);

    int getMaxId();
}
