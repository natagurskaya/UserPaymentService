package com.study.payments.servlet;

import com.study.payments.entity.User;
import com.study.payments.json.JsonConverter;
import com.study.payments.service.UserService;
import com.study.payments.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String htmlPage="users.html";

        List<User> users = userService.getAll();
        pageVariables.put("users", users);

        PageGenerator instance = PageGenerator.getInstance();
        String page = instance.getPage(htmlPage, pageVariables);

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }
}
