package com.easychat.core.service.impl;

import com.easychat.core.entity.User;
import com.easychat.core.repository.UserRepository;
import com.easychat.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        User userToUpdate = repo.findById(user.getId()).orElseThrow(Exception::new);
        return repo.save(user);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        User userToDelete = repo.findById(user.getId()).orElseThrow(Exception::new);
        repo.delete(user);
    }
}
