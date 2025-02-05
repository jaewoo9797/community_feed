package org.fastcampus.post.domain.comment;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(User author, Post post, String content) {
        return new Comment(null,post,author,new CommentContent(content));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Comment(Long id, Post post, User author, CommentContent content) {
        Objects.requireNonNull(post);
        Objects.requireNonNull(author);
        Objects.requireNonNull(content);
        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User user, String updateContent) {
        if (!user.equals(author)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public CommentContent getContent() {
        return content;
    }

    public PositiveIntegerCounter getLikeCount() {
        return likeCount;
    }
}
