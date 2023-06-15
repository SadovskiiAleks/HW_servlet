package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.stream.Collectors;

// Stub
public class PostRepository {

  private HashMap<Long,Post> mapOfPost = new HashMap<>();
  private long id = 0l;

  public List<Post> all() {
    List<Post> s = mapOfPost.values().stream().collect(Collectors.toList());
    return s;
  }

  public Optional<Post> getById(long id) {
    Optional<Post> s = Optional.ofNullable(mapOfPost.get(id));
    return s;
  }

  public Post save(Post post) {
    mapOfPost.put(++id,post);
    return post;
  }

  public void removeById(long id) {
    mapOfPost.remove(id);
  }

}
