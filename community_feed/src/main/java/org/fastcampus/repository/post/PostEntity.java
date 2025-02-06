package org.fastcampus.repository.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.repository.common.TimeBaseEntity;
import org.fastcampus.repository.user.UserEntity;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    private Integer likeCount;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;


    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent().getContentText();
        this.likeCount = post.getLikeCount().getCount();
        this.state = post.getState();
    }

    public Post toPost() {
        return Post.builder()
            .id(id)
            .author(author.toUser())
            .content(new PostContent(content))
            .likeCount(new PositiveIntegerCounter(likeCount))
            .state(state)
            .build();
    }
}
