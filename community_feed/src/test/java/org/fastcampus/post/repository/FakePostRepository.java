package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (store.containsKey(post.getId())) {
            return store.put(post.getId(), post);
        }
        long id = store.size() + 1L;
        Post createPost = new Post(id, post.getAuthor(), post.getContent(), post.getState());
        store.put(id, createPost);
        return createPost;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
