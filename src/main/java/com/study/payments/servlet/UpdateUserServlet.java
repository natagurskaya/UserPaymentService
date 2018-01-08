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

public class UpdateUserServlet extends HttpServlet {
    private UserService userService = new UserService();
    private String requestedPage="update_user.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter("id")));

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "");
        pageVariables.put("id", user.getId());
        pageVariables.put("firstName",user.getFirstName());
        pageVariables.put("lastName",user.getLastName());
        pageVariables.put("payment",user.getPayment());

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
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPayment(Double.parseDouble(req.getParameter("payment")));

        try {
            userService.update(user);
            status = "Successfully updated";
        } catch (RuntimeException e) {
            resp.sendRedirect("/errorPage");
        }

        resp.sendRedirect("/users");
    }

}
