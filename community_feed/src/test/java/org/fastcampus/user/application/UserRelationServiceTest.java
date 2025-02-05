package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService =
        new UserRelationService(userService, userRelationRepository);


    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;
    @BeforeEach
    void setUp() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);

        requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void 두유저_팔로우_유저팔로우정보저장() {
        // given
        userRelationService.follow(requestDto);
        // when

        // then
        Assertions.assertEquals(1, user1.getFollowingCount());
        Assertions.assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void 두_유저_이미팔로우상태_한번_더_팔로우시_에러throw() {
        // given
        userRelationService.follow(requestDto);
        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class,() -> userRelationService.follow(requestDto));
    }
}