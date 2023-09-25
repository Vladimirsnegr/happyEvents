package com.example.happyEvents.map;

import com.example.happyEvents.dto.UserDto;
import com.example.happyEvents.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToDto (User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public User dtoToUser (UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
