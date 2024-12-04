package com.webmovie.filter;



import com.webmovie.daoimples.LogDAOImple;
import com.webmovie.daos.LogDAO;
import com.webmovie.entities.Log;
import com.webmovie.entities.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebFilter({"/admin/*"})
public class FilterSercureInfo extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getAdmin()){
                LogDAO ldao = new LogDAOImple();
                User u = (User)request.getSession().getAttribute("user");
                String uri = request.getRequestURI();
                Log log = new Log(uri,new Date(),u.getFullname());
                ldao.create(log);
                chain.doFilter(request, response);
            }else{
                request.getSession().setAttribute("sercureUri",request.getRequestURI());
                response.sendRedirect("/TMovies/login");
            }
        }else{
            request.getSession().setAttribute("sercureUri",request.getRequestURI());
            response.sendRedirect("/TMovies/login");
        }
    }
}

