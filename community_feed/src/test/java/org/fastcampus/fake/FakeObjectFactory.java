package org.fastcampus.fake;

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakeLikeRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository USER_REPOSITORY = new FakeUserRepository();
    private static final UserRelationRepository USER_RELATION_REPOSITORY = new FakeUserRelationRepository();
    private static final PostRepository POST_REPOSITORY = new FakePostRepository();
    private static final CommentRepository COMMENT_REPOSITORY = new FakeCommentRepository();
    private static final LikeRepository LIKE_REPOSITORY = new FakeLikeRepository();

    private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);
    private static final UserRelationService USER_RELATION_SERVICE =
        new UserRelationService(USER_SERVICE, USER_RELATION_REPOSITORY);
    private static final PostService POST_SERVICE =
        new PostService(USER_SERVICE, POST_REPOSITORY, LIKE_REPOSITORY);
    private static final CommentService COMMENT_SERVICE =
        new CommentService(COMMENT_REPOSITORY, USER_SERVICE, POST_SERVICE, LIKE_REPOSITORY);

    private FakeObjectFactory() {
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static UserRelationService getUserRelationService() {
        return USER_RELATION_SERVICE;
    }

    public static PostService getPostService() {
        return POST_SERVICE;
    }

    public static CommentService getCommentService() {
        return COMMENT_SERVICE;
    }

}
