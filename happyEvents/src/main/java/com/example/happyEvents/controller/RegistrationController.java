package com.example.happyEvents.controller;

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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

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

    /*@PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpSession httpSession, HttpServletResponse response) {
        // Создаем аутентификационный объект
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            // Попытка аутентификации с использованием AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // Если аутентификация прошла успешно
            if (authentication.isAuthenticated()) {
                httpSession.setAttribute("authenticated", true);
                // Устанавливаем JSESSIONID в куки
                Cookie cookie = new Cookie("JSESSIONID", httpSession.getId());
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseEntity.ok("Login successful");
            }
        } catch (org.springframework.security.core.AuthenticationException e) {
            // Обработка ошибки аутентификации
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
        // В случае неудачной аутентификации
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }*/
}
