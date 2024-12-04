package com.webmovie.controllers;


import java.io.IOException;
import java.util.Base64;

import com.webmovie.daoimples.UserDAOImple;
import com.webmovie.daos.UserDAO;
import com.webmovie.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author acer
 */
@WebServlet({"/login"})
public class LoginController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encoded = cookie.getValue();
                    byte[] bytes = Base64.getDecoder().decode(encoded);
                    String[] userInfo = new String(bytes).split(",");
                    request.setAttribute("username", userInfo[0]);
                    request.setAttribute("password", userInfo[1]);
                }
            }
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String sercureUri = (String)session.getAttribute("sercureUri");
        if(user !=null){
            if((!user.getAdmin() && (sercureUri.contains("admin"))))
                request.setAttribute("message", "Vui lòng đăng nhập với quyền admin !");
        }
        request.getRequestDispatcher("/views/assets/views/Login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("rememberMe");
        UserDAO dao = new UserDAOImple();
        User user = dao.findByIdOrEmail(username.trim());
        HttpSession session = request.getSession();
        String sercureUri = (String) session.getAttribute("sercureUri");
        if (sercureUri == null) {
            sercureUri = "/TMovies/home/index";
        }
        if (user != null) {
            if (password.equals(user.getPassword())) {
                if (remember != null) {
                    byte[] bytes = (username + "," + password).getBytes();
                    String userInfo = Base64.getEncoder().encodeToString(bytes);
                    Cookie cookie = new Cookie("user", userInfo);
                    cookie.setMaxAge(30 * 24 * 60 * 60); // hiệu lực 30 ngày
                    cookie.setPath("/"); // hiệu lực toàn ứng dụng
                    // Gửi v�? trình duyệt
                    response.addCookie(cookie); // cookie
                } else {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("user")) {
                                cookie.setValue("");
                                cookie.setMaxAge(0);
                                cookie.setPath("/");
                                response.addCookie(cookie);
                                break;
                            }
                        }
                    }
                }
                request.getSession().setAttribute("user", user);
                if (user.getAdmin()) {
                    response.sendRedirect("/TMovies/admin/home");
                    return;
                } else {
                    response.sendRedirect(sercureUri);
                    return;
                }
            }
        }
        request.setAttribute("message","Username or password incorrect");
        request.getRequestDispatcher("/views/assets/views/Login.jsp").forward(request, response);
    }
}
