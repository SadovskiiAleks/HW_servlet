package ru.netology.servlet;

import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    private static final String post = "/api/posts";
    private static final String postWithParam = "/api/posts/\\d+";
    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            String path = req.getRequestURI();
            String method = req.getMethod();
            Long id = 0l;
            // primitive routing
            if (method.equals(MethodOfRequest.GET.toString()) && path.equals(post)) {
                controller.all(resp);
                return;
            }
            if (method.equals(MethodOfRequest.GET.toString()) && path.matches(postWithParam)) {
                // easy way
                id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.getById(id, resp);
                return;
            }
            if (method.equals(MethodOfRequest.POST.toString()) && path.equals(post)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(MethodOfRequest.DELETE.toString()) && path.matches(postWithParam)) {
                // easy way
                id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

