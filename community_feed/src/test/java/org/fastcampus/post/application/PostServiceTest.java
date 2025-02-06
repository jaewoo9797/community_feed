package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();

    private final User user = userService.createUser(new CreateUserRequestDto("test", ""));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("test", ""));

    private final CreatePostRequestDto createPostRequestDto =
        new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);



    @Test
    void 포스트생성_메소드호출_포스트리턴() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);
        // when

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(post.getId(), savedPost.getId());
    }

    @Test
    void 업데이트_포스트_리턴_업데이트된_포스트() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);
        String oldContent = savedPost.getContent().getContentText();
        final UpdatePostRequestDto updatePostRequestDto =
            new UpdatePostRequestDto(savedPost.getId(), "update post content",
                PostPublicationState.PUBLIC);
        // when
        Post updatePost = postService.updatePost(user.getId(), updatePostRequestDto);
        // then

        assertEquals(updatePost.getId(), savedPost.getId());
        assertNotEquals(updatePost.getContent().getContentText(), oldContent);
    }
}