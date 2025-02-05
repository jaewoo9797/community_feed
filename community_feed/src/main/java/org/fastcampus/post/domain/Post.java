package org.fastcampus.post.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content, PostPublicationState state) {
        if (Objects.isNull(author)) {
            throw new IllegalArgumentException("author is null");
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
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

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public Long getId() {
        return id;
    }
    public User getAuthor() {
        return author;
    }

    public PostContent getContent() {
        return content;
    }

    public PostPublicationState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
