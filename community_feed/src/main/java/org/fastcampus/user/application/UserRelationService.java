package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUserById(dto.userId());
        User tagetUser = userService.getUserById(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, tagetUser)) {
            throw new IllegalArgumentException();
        }

        user.follow(tagetUser);
        userRelationRepository.save(user, tagetUser);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUserById(dto.userId());
        User tagetUser = userService.getUserById(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, tagetUser)) {
            throw new IllegalArgumentException();
        }

        user.unfollow(tagetUser);
        userRelationRepository.delete(user, tagetUser);
    }
}
