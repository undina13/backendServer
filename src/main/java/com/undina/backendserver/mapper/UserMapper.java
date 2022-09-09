package com.undina.backendserver.mapper;

import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
             //   .contact(userDto.getContact())
                .build();
    }

    public static UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
            //    .contact(user.getContact())
                .build();
    }

}
