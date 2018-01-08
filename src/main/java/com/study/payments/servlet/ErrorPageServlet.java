package com.study.payments.servlet;

import com.study.payments.entity.User;
import com.study.payments.service.UserService;
import com.study.payments.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ErrorPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String htmlPage="error_page.html";

        PageGenerator instance = PageGenerator.getInstance();
        String page = instance.getPage(htmlPage, pageVariables);

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }
}
