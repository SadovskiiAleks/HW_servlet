package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
public class PostRepository {
    List<String> s = new ArrayList<>();
    private ConcurrentHashMap<Long, Post> mapOfPost = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public PostRepository() {
        lastId.set(0l);
    }

    public List<Post> all() {
        List<Post> s = mapOfPost.values().stream().collect(Collectors.toList());
        return s;
    }

    public Optional<Post> getById(long id) {
        Optional<Post> s = Optional.ofNullable(mapOfPost.get(id));
        return s;
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
