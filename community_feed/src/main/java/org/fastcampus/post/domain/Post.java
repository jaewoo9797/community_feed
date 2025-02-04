package org.fastcampus.post.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content) {
        if (Objects.isNull(author)) {
            throw new IllegalArgumentException("author is null");
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        state = PostPublicationState.PUBLIC;
    }

    public void like(User user) {
        if (user.equals(author)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike(User user) {
        if (user.equals(author)) {
            throw new IllegalArgumentException();
        }
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState updateState) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.state = updateState;
        this.content.updateContent(updateContent);
    }
}
