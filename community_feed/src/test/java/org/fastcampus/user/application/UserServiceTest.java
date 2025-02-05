package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("abc", "");
        // when
        User savedUser = userService.createUser(dto);
        User foundUser = userService.getUserById(savedUser.getId());
        UserInfo foundUserInfo = foundUser.getInfo();
        // then
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals(foundUserInfo.getName(), "abc");
    }
}