package com.webmovie.controllers.admin;


import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.VideoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/admin/home/index"
})
public class AdminHomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VideoDAO dao = new VideoDAOImple();


        request.setAttribute("listVideoActive",dao.getTopVideoActive(0,10,true));
        request.getRequestDispatcher("/views/assets/views/AdminHomePage.jsp").forward(request, response);
    }
}
