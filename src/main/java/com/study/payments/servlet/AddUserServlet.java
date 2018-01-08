package com.study.payments.servlet;

import com.study.payments.entity.User;
import com.study.payments.service.UserService;
import com.study.payments.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet {
    private UserService userService = new UserService();
    private String requestedPage="add_user.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("id", userService.getMaxId()+1);

        PageGenerator instance = PageGenerator.getInstance();
        String page = instance.getPage(requestedPage, pageVariables);

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status="";
        User user = new User();

       // user.setId(Integer.parseInt(req.getParameter("id")));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPayment(Double.parseDouble(req.getParameter("payment")));

        try {
            userService.save(user);
        } catch (RuntimeException e) {
            resp.sendRedirect("/errorPage");
        }

        resp.sendRedirect("/addUser");
    }

}
