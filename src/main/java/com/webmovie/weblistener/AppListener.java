package com.webmovie.weblistener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    public AppListener() {

    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Integer userOnline = 0;
        sce.getServletContext().setAttribute("countUsers", userOnline);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Weblistener closed");
    }

}
