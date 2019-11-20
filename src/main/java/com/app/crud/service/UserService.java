package com.app.crud.service;

import com.app.crud.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User findUserById(Long id);

    Page<User> getAllUsers(Integer page, Integer size);

    User saveUser(User User);

    User updateUser(User User);

    void deleteUser(Long id);
}
