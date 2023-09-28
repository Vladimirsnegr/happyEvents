package com.example.happyEvents.controller;

import com.example.happyEvents.dto.LoginDto;
import com.example.happyEvents.dto.UserDto;
import com.example.happyEvents.map.UserMapper;
import com.example.happyEvents.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class RegistrationController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/api/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Login successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Login failed",HttpStatus.UNAUTHORIZED);
        }
    }
}
