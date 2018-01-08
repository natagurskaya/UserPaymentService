package com.study.payments;

import com.study.payments.dao.UserDao;
import com.study.payments.dao.jdbc.JdbcUserDao;
import com.study.payments.servlet.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static com.study.payments.configuration.Constants.webAppPath;

public class Starter {
    public static void main(String[] args) throws Exception {
        // Create Dao
        UserDao userDao = new JdbcUserDao();

        //Configure ServiceLocator
        ServiceLocator.register(UserDao.class, userDao);

        // Handlers Configuration
        HandlerList handlers = new HandlerList();
        // Resources
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(webAppPath);
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setRedirectWelcome(true);
        // Servlets
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new AddUserServlet()), "/addUser");
        contextHandler.addServlet(new ServletHolder(new UpdateUserServlet()), "/updateUser");
        contextHandler.addServlet(new ServletHolder(new DeleteUserServlet()), "/deleteUser");
        contextHandler.addServlet(new ServletHolder(new UsersServlet()), "/users");
        contextHandler.addServlet(new ServletHolder(new ErrorPageServlet()), "/errorPage");

        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
