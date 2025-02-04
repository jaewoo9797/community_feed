package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void writeHereTestName() {
        // given

        // when

        // then
    }
}