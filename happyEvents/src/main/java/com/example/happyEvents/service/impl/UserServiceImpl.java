package com.example.happyEvents.service.impl;

import com.example.happyEvents.entity.User;
import com.example.happyEvents.enums.Role;
import com.example.happyEvents.rep.UserRepository;
import com.example.happyEvents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    @Override
    public void saveUser(User user)  {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ROLE_USER);
        roleSet.add(Role.ROLE_ADMIN);
        user.setRoles(roleSet);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
