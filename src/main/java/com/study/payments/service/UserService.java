package com.study.payments.service;

import com.study.payments.ServiceLocator;
import com.study.payments.dao.UserDao;
import com.study.payments.entity.User;

import java.util.List;

public class UserService implements UserDao {

    private UserDao userDao = ServiceLocator.get(UserDao.class);

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public int getMaxId() {
        return userDao.getMaxId();
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
