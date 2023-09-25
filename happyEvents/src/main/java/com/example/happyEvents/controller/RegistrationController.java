package com.example.happyEvents.controller;

import com.example.happyEvents.dto.UserDto;
import com.example.happyEvents.map.UserMapper;
import com.example.happyEvents.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    //@CrossOrigin(origins = "http://25.33.240.196:5173", allowCredentials = "true")
    @PostMapping("/api/registration")
    public void registration(@RequestBody UserDto dto) {
        userService.saveUser(userMapper.dtoToUser(dto));
    }

    @GetMapping("/api/check-auth")
    public boolean checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что текущая аутентификация не анонимная и пользователь авторизован
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            return true;
        }

        return false;
    }
}
