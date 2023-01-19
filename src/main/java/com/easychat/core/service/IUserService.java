package com.easychat.core.service;

import com.easychat.core.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Integer id) throws Exception;
    User createUser(User user);
    User updateUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
}
