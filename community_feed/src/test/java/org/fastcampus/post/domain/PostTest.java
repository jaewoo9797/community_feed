package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    private final Post post = new Post(1L, user1, new PostContent("test content"), PostPublicationState.PUBLIC);
    @Test
    void 좋아요_메소드실행시_좋아요1증가() {
        // given
        post.like(user2);
        // when
        // then
        assertEquals(post.getLikeCount(),1);
    }


    @Test
    void 작성자가_좋아요_실행시_에러throw() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> post.like(user1));
    }
}