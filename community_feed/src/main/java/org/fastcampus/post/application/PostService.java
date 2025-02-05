package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService,PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post createPost(CreatePostRequestDto requestDto) {
        User author = userService.getUserById(requestDto.userId());
        Post post = new Post(null, author, new PostContent(requestDto.content()), requestDto.state());
        return postRepository.save(post);
    }

    public Post getPost(Long id){
        return postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post updatePost(Long userId, UpdatePostRequestDto requestDto) {
        User foundUser = userService.getUserById(userId);
        Post post = getPost(requestDto.postId());

        post.updatePost(foundUser,requestDto.content(),requestDto.state());
        return postRepository.save(post);
    }

    public void likePost(Long userId, LikeRequestDto requestDto) {
        Post post = getPost(userId);
        User foundUser = userService.getUserById(requestDto.targetId());

        if (likeRepository.checkLike(post,foundUser)) {
            return;
        }

        post.like(foundUser);
        likeRepository.like(post,foundUser);
    }

    public void unlikePost(Long userId, LikeRequestDto requestDto) {
        Post post = getPost(userId);
        User foundUser = userService.getUserById(requestDto.targetId());

        if (likeRepository.checkLike(post,foundUser)) {
            post.unlike(foundUser);
            likeRepository.unlike(post,foundUser);
        }

    }
}
