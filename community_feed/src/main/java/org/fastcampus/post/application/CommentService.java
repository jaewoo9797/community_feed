package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;

    public CommentService(
        CommentRepository commentRepository,
        UserService userService,
        PostService postService,
        LikeRepository likeRepository
    ) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(Long userId, CreateCommentRequestDto requestDto) {
        User author = userService.getUserById(userId);
        Post foundPost = postService.getPost(requestDto.postId());

        Comment createComment = Comment.createComment(author, foundPost, requestDto.content());
        return commentRepository.save(createComment);
    }

    public Comment updateComment(Long userId, UpdateCommentRequestDto requestDto) {
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUserById(userId);
        comment.updateComment(user, requestDto.updateContent());
        return commentRepository.save(comment);
    }

    public void likeComment(Long userId, LikeRequestDto requestDto) {
        Comment comment = getComment(requestDto.targetId());
        User user = userService.getUserById(userId);

        if (likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(Long userId, LikeRequestDto requestDto) {
        Comment comment = getComment(requestDto.targetId());
        User user = userService.getUserById(userId);

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike(user);
            likeRepository.unlike(comment, user);
        }
    }
}
