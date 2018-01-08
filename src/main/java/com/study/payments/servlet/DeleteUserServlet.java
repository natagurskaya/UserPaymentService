package com.study.payments.servlet;

import com.study.payments.entity.User;
import com.study.payments.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    private UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter("id")));

        try {
            userService.delete(user);
        } catch (RuntimeException e) {
            resp.sendRedirect("/errorPage");
        }

        resp.sendRedirect("/users");
    }
}
