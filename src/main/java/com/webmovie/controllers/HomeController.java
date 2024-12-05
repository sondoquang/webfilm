package com.webmovie.controllers;


import com.webmovie.daoimples.FavouriteDAOImple;
import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.FavouriteDAO;
import com.webmovie.daos.VideoDAO;
import com.webmovie.entities.User;
import com.webmovie.entities.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/home/index/*",
        "/home/suggest/*",
        "/home/search"
})
public class HomeController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        VideoDAO vdao = new VideoDAOImple();
        FavouriteDAO fdao = new FavouriteDAOImple();
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");

        /*List main video*/
        List<Video> list01 = null;
        List<Video> list02 = null;
        List<Video> listTopFavourite = fdao.getTop10VideosFavourite(10);
        List<Video> listFavoutiteOfUserOnline = null;
        long sumCountVideo = vdao.selectAllCountVideo();

        /* Kiểm tra nếu user đang đăng nhập*/
        if(user != null){
            listFavoutiteOfUserOnline = fdao.getVideoFavouritesByUser(user.getId());
            request.setAttribute("listFavourite", listFavoutiteOfUserOnline);
        }
        int pageNo01 = 1;
        int pageNo02 = 1;
        if (path.contains("index")){
            try {
                String sortBy = request.getParameter("sortBy");
                if(!sortBy.isEmpty()){
                    pageNo01 = !request.getParameter("page1").isEmpty()?Integer.parseInt(request.getParameter("page1")):1;
                    list01 = vdao.findAllByTitleOrStatus((pageNo01-1)*16,16,"",Boolean.parseBoolean(sortBy));
                    sumCountVideo = vdao.selectSumCountVideoActive(Boolean.parseBoolean(sortBy));
                    request.setAttribute("list01", list01);
                    request.setAttribute("isActive", Boolean.parseBoolean(sortBy));
                }else{
                    pageNo01 = !request.getParameter("page1").isEmpty()?Integer.parseInt(request.getParameter("page1")):1;
                }
            } catch (Exception e) {

            }
        }

        /* Xử lý suggest 02*/
        else if (path.contains("suggest")){
            try {
                String sortBy = request.getParameter("sortBy");
                if(!sortBy.isEmpty()){
                    pageNo02 = !request.getParameter("page2").isEmpty()?Integer.parseInt(request.getParameter("page2")):1;
                    list02 = vdao.findAllByTitleOrStatus((pageNo02-1)*16,16,"",Boolean.parseBoolean(sortBy));
                    sumCountVideo = vdao.selectSumCountVideoActive(Boolean.parseBoolean(sortBy));
                    request.setAttribute("list02", list02);
                    request.setAttribute("isActive", Boolean.parseBoolean(sortBy));
                }else{
                    pageNo02 = !request.getParameter("page2").isEmpty()?Integer.parseInt(request.getParameter("page2")):1;
                }
            } catch (Exception e) {

            }

        }


        /* Check null 2 list quan trọng*/
        if(list01 == null) {
            int pageNo1 = request.getParameter("page1")!=null?Integer.parseInt(request.getParameter("page1")):1;
            request.setAttribute("list01", vdao.findAll((pageNo1-1)*16,16));
        }
        if(list02 == null) {
            int pageNo2 = request.getParameter("page2")!=null?Integer.parseInt(request.getParameter("page2")):1;
            request.setAttribute("list02", vdao.findAll((pageNo2-1)*16,16));
        }
        /*Đếm sum count trong table video và phân trang*/
        int sumPage;
        if (sumCountVideo%16 == 0){
            sumPage =(int) sumCountVideo/16;
        }else{
            sumPage =(int) sumCountVideo/16+1;
        }


        request.setAttribute("pageNo01", pageNo01);
        request.setAttribute("pageNo02", pageNo02);
        request.setAttribute("sumPage",sumPage);
        request.setAttribute("listTopFavourite",listTopFavourite);
        request.getRequestDispatcher("/views/Home.jsp").forward(request, response);
    }
}
