package com.webmovie.api;


import com.webmovie.daoimples.FavouriteDAOImple;
import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.FavouriteDAO;
import com.webmovie.daos.VideoDAO;
import com.webmovie.entities.Favourite;
import com.webmovie.entities.User;
import com.webmovie.utils.XJsonIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

/**
 *
 * @author acer
 */
@WebServlet({"/api/videos/*"})
public class VideoApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getPathInfo().substring(1));
        VideoDAO vdao = new VideoDAOImple();
        FavouriteDAO fdao = new FavouriteDAOImple();
        User user = (User) request.getSession().getAttribute("user");
        Favourite favourite = new Favourite();
        favourite.setUser(user);
        favourite.setVideo(vdao.findById(id));
        if(fdao.updateLikeVideoFavourite(favourite) != -1){
            XJsonIO.writeObject(response, new Favourite());
        };
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavouriteDAO dao = new FavouriteDAOImple();
        Integer videoId = Integer.parseInt(request.getPathInfo().substring(1));
        User user = (User) request.getSession().getAttribute("user");
        Favourite favourite = dao.selectVideoByUserIdAndVideoId(videoId,user.getId());
        if (dao.deleteById(favourite.getId()) !=null){
            XJsonIO.writeObject(response, new Favourite());
        }
    }
}

