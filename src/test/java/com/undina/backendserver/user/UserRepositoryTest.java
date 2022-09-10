package com.undina.backendserver.user;

import com.undina.backendserver.model.Role;
import com.undina.backendserver.model.User;
import com.undina.backendserver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class UserRepositoryTest {

    User user = User.builder().name("user3444").email("useryuyu@mail.ru").password("123").role(Role.USER).build();
    @Autowired
    private UserRepository userRepository;

    @Test
    @DirtiesContext
    void testSaveUser() {
        assertThat(user.getId(), equalTo(null));
        userRepository.save(user);
        assertThat(user.getId(), notNullValue());
    }
}
