package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
public interface PostRepository {

    public List<Post> all();

    public Optional<Post> getById(long id);
    public Post save(Post post);

    public void removeById(long id);
}
