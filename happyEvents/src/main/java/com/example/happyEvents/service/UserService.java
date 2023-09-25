package com.example.happyEvents.service;

import com.example.happyEvents.entity.User;

public interface UserService {
    User getUser(Long id);

    void saveUser(User user);
}
