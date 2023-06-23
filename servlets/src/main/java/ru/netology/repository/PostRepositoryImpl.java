package ru.netology.repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class PostRepositoryImpl implements PostRepository {
    final private Map<Long, Post> mapOfPost = new ConcurrentHashMap<>();
    final private AtomicLong lastId = new AtomicLong();

    public PostRepositoryImpl() {
        lastId.set(0l);
    }

    public List<Post> all() {
        return new ArrayList<>(mapOfPost.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(mapOfPost.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0l) {
            post.setId(lastId.incrementAndGet());
        } else if (!mapOfPost.containsKey(post.getId())) {
            post.setId(lastId.incrementAndGet());
        }
        mapOfPost.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        mapOfPost.remove(id);
    }
}
