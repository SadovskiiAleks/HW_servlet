package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> mapOfPost = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);
    private final long startID = 0l;


    public List<Post> all() {
        return new ArrayList<>(mapOfPost.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(mapOfPost.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == startID) {
            post.setId(lastId.incrementAndGet());
        } else if (!mapOfPost.containsKey(post.getId())) {
            post.setId(lastId.incrementAndGet());
        }
        mapOfPost.putIfAbsent(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        mapOfPost.remove(id);
    }
}
