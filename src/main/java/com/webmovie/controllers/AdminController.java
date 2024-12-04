package com.webmovie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/admin/home",
        "/admin/videos",
        "/admin/users",
        "/admin/reports"
})
public class AdminController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        if (path.contains("home")) {
            session.setAttribute("menuActive",1);
            response.sendRedirect("/TMovies/admin/home/index");
        }
        else if(path.contains("videos")) {
            session.setAttribute("menuActive", 2);
            response.sendRedirect("/TMovies/admin/videos/index");
        }
        else if(path.contains("users")){
            session.setAttribute("menuActive",3);
            response.sendRedirect("/TMovies/admin/user/index");
        }
        else if(path.contains("reports")){
            session.setAttribute("menuActive",4);
            response.sendRedirect("/TMovies/admin/reports/index");
        }
    }
}

