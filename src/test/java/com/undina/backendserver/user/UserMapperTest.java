package com.undina.backendserver.user;

import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.mapper.UserMapper;
import com.undina.backendserver.model.User;
import org.junit.jupiter.api.Test;


import static com.undina.backendserver.user.UserTestData.user1;
import static com.undina.backendserver.user.UserTestData.userDto1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UserMapperTest {
    @Test
    public void toUserDto() {
        UserDto userDto = UserMapper.toUserDto(user1);
        assertThat(userDto, equalTo(userDto1));
    }

    @Test
    public void toUser() {
        User user = UserMapper.toUser(userDto1);
        assertThat(user, equalTo(user1));
    }
}
