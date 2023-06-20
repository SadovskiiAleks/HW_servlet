package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    private static List<Post> data = new ArrayList<>();
    // POST статик не сделать
    private static Post dataOfOnePost;
    private static final Gson gson = new Gson();


    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        this.data = service.all();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        this.dataOfOnePost = service.getById(id);
        response.getWriter().print(gson.toJson(dataOfOnePost));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        dataOfOnePost = gson.fromJson(body, Post.class);
        service.save(dataOfOnePost);
        response.getWriter().print(gson.toJson(dataOfOnePost));
    }

    public void removeById(long id, HttpServletResponse response) {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.setStatus(200);
    }
}
