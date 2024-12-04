package com.webmovie.controllers;

import com.webmovie.daoimples.FavouriteDAOImple;
import com.webmovie.daoimples.ShareDAOImple;
import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.FavouriteDAO;
import com.webmovie.daos.ShareDAO;
import com.webmovie.daos.VideoDAO;
import com.webmovie.entities.Favourite;
import com.webmovie.entities.Share;
import com.webmovie.entities.User;
import com.webmovie.entities.Video;
import com.webmovie.utils.XMailer;
import com.webmovie.utils.XValidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author acer
 */
@WebServlet({"/videos/like/*",
        "/videos/share/*",
        "/videos/detail/*"
})
public class VideoController extends HttpServlet {
    FavouriteDAO fdao = new FavouriteDAOImple();
    ShareDAO sdao = new ShareDAOImple();
    VideoDAO dao = new VideoDAOImple();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        VideoDAO dao = new VideoDAOImple();


        /*== Xử lý cho video detail ==*/
        if (path.contains("detail")){
            Integer id = Integer.parseInt(request.getPathInfo().substring(1));
            Video video = dao.findById(id);
            video.setViews(video.getViews() + 1);
            dao.update(video);
            request.setAttribute("video",video);
            request.setAttribute("videofavourite",fdao.getTop10VideosFavourite(10));
            request.getRequestDispatcher("/views/assets/views/DetailProduct.jsp").forward(request, response);
        }


        /*== Xử lý sự kiện like video ==*/
        else if (path.contains("like")){
            if (fdao.create(getEntityFavourite(request)) == null){
                request.setAttribute("message","Thao tác không thành công !!");
            }else{
                request.setAttribute("message","Thao tác thành công !!");
            }
            String sercureUri =(String)request.getSession().getAttribute("sercureUri");
            response.sendRedirect(sercureUri);
        }

        /*== Xử lý sự kiện share video ==*/
        else if (path.contains("share")){
            String emailTo = request.getParameter("emailTo");
            emailTo = emailTo.trim();
            if (!XValidate.email(emailTo)){
                request.setAttribute("message","Sự cố share video !");
                response.sendRedirect((String)request.getSession().getAttribute("sercureUri"));
                return;
            }
            try {
                XMailer.send(
                        emailTo,
                        "Share video favourite from website TMovies by SonDoItNow development",
                        getBodyEmailSendCustomer((String)request.getSession().getAttribute("sercureUri")));
                sdao.create(getEntityShare(request,emailTo));
            }catch (Exception e){
                e.printStackTrace();
            }
            response.sendRedirect((String)request.getSession().getAttribute("sercureUri"));
        }
    }


    /* Hàm lấy ra thông tin video được yêu thích và tạo một Entity Favourite */
    private Favourite getEntityFavourite(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getPathInfo().substring(1));
        User user = (User) request.getSession().getAttribute("user");
        Video video = dao.findById(id);
        return new Favourite(user,video,new Date());
    }


    /* Hàm lấy ra thông tin video được share và tạo một Entity Share */
    private Share getEntityShare(HttpServletRequest request, String emailTo){
        Integer id = Integer.parseInt(request.getPathInfo().substring(1));
        User user = (User) request.getSession().getAttribute("user");
        Video video = dao.findById(id);
        return new Share(user,video,emailTo,new Date());
    }

    private String getBodyEmailSendCustomer(String uri ){
        return "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Greetings from SonDoItNow</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            color: #333;\n" +
                "            line-height: 1.6;\n" +
                "            margin: 0;\n" +
                "            padding: 20px;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h2 {\n" +
                "            color: #4CAF50;\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-size: 16px;\n" +
                "            color: #555;\n" +
                "            margin: 15px 0;\n" +
                "        }\n" +
                "        a {\n" +
                "            color: #007BFF;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            margin-top: 30px;\n" +
                "            font-size: 14px;\n" +
                "            text-align: center;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <h2>Hello,</h2>\n" +
                "\n" +
                "        <p>Thank you for choosing <strong>SonDoItNow</strong>. We're excited to provide you with the video link on our website, <strong><a href=\"https://www.tmovies.com\" target=\"_blank\">TMovies</a></strong>.</p>\n" +
                "        <p>Please click link here</p>\n" +
                "<p>https://67d5-2001-ee0-4f4b-2f0-3da7-d359-74ab-1541.ngrok-free.app"+uri+"</p>"+
                "        <p>We hope you enjoy your viewing experience and have a great time!</p>\n" +
                "        <p>Best regards,<br>\n" +
                "        The <strong>SonDoItNow</strong> Team</p>\n" +
                "        \n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 SonDoItNow. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

}
