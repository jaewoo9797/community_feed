package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1 = new User(1L, userInfo);
    private User user2 = new User(2L, userInfo);

    @Test
    void id가_다른_유저_두_명이_주어졌을때_두유저가같은지검사_false리턴() {
        // given
        // when
        boolean isSame = user1.equals(user2);
        // then
        assertFalse(isSame);
    }

    @Test
    void id가_같은_유저_동일검사_true리턴() {
        // given
        User sameUser = new User(1L, userInfo);
        // when
        boolean isSame = user1.equals(sameUser);
        // then
        assertTrue(isSame);
    }

    @Test
    void id가_같은_두_유저_해쉬코드동일검사_true리턴() {
        // given
        User sameUser = new User(1L, userInfo);
        // when
        boolean isSame = user1.hashCode() == user1.hashCode();
        // then
        assertEquals(sameUser.hashCode(), user1.hashCode());
    }

    @Test
    void id가_다른_두_유저_해쉬코드동일검사_false리턴() {
        // given
        int user1HashCode = user1.hashCode();
        int user2HashCode = user2.hashCode();
        // when
        boolean isSame = user1HashCode == user2HashCode;
        // then
        assertFalse(isSame);
    }

    @Nested
    @DisplayName("팔로우_검증")
    class follow {

        @BeforeEach
        void init() {
            user1 = new User(1L, userInfo);
            user2 = new User(2L, userInfo);
        }

        @Test
        void 다른_두_유저_팔로우메소드_팔로우숫자증가() {
            // given

            // when
            user1.follow(user2);
            // then
            assertEquals(1, user1.getFollowingCount());
            assertEquals(0, user1.getFollowerCount());
            assertEquals(0, user2.getFollowingCount());
            assertEquals(1, user2.getFollowerCount());
        }

        @Test
        void 다른_두_유저_팔로우_후_언팔_팔로우숫자감소() {
            // given
            user1.follow(user2);
            // when
            user1.unfollow(user2);
            // then
            assertEquals(0, user1.getFollowingCount());
            assertEquals(0, user1.getFollowerCount());
            assertEquals(0, user2.getFollowingCount());
            assertEquals(0, user2.getFollowerCount());
        }

        @Test
        void 두_유저_팔로우_전_언팔_팔로우숫자는0() {
            // given
            // when
            user1.unfollow(user2);
            // then
            assertEquals(0, user1.getFollowingCount());
            assertEquals(0, user1.getFollowerCount());
            assertEquals(0, user2.getFollowingCount());
            assertEquals(0, user2.getFollowerCount());
        }
    }
}