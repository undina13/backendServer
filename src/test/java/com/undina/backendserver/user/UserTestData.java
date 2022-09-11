package com.undina.backendserver.user;


import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.model.Role;
import com.undina.backendserver.model.User;

public class UserTestData {
    public static final UserDto userDto1 =
            UserDto.builder().id(1L).name("user").email("user1@mail.ru")
                    .password("$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6").role(Role.USER).build();
    public static final UserDto userDto2 =
            UserDto.builder().id(2L).name("user2").email("user1rere@mail.ru")
                    .password("$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6").role(Role.ADMIN).build();
    public static final UserDto userDto3 =
            UserDto.builder().id(3L).name("user3").email("user1rhere@mail.ru")
                    .password("$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6").role(Role.ADMIN).build();
    public static final UserDto userDtoCreated =
            UserDto.builder().id(4L).name("user4").email("user1rerere@mail.ru").password("123").role(Role.USER).build();

    public static final User user1 =  User.builder().id(1L).name("user").email("user1@mail.ru").password("$2a$12$ZHKME4W/x8G9RNNDy.hnKe6llXgdGLnDoDxFthnIrGRNxbs4H03a6").role(Role.USER).build();
    public static final User user2 =  User.builder().id(2L).name("user2").email("user1rere@mail.ru").password("123").role(Role.ADMIN).build();
}
