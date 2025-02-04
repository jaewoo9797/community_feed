package org.fastcampus.post.domain;

import java.util.Objects;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;

    public Post(Long id, User author, PostContent content) {
        if (Objects.isNull(author)) {
            throw new IllegalArgumentException("author is null");
        }
        this.id = id;
        this.author = author;
        this.content = content;
    }
}
