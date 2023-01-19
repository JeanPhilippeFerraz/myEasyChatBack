package com.easychat.core.mapper;

import com.easychat.core.Controller.dto.UserDto;
import com.easychat.core.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User mapUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
