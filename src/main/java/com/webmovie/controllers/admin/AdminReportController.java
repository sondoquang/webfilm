package com.webmovie.controllers.admin;


import com.webmovie.daoimples.ReportDAOImple;
import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.ReportDAO;
import com.webmovie.daos.VideoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/reports/index/*",
        "/admin/reports/search",
        "/admin/reports/favouritesUser",
        "/admin/reports/favouritesShare",
})
public class AdminReportController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportDAO dao = new ReportDAOImple();
        String path = request.getServletPath();
        String search = request.getParameter("search");
        int pageNo = request.getParameter("pageNo")!=null?Integer.parseInt(request.getParameter("pageNo")):1;
        List<Object[]> list = dao.selectVideoFavoritesByMaxMinDate((pageNo-1)*12,12);
        int sumPage = Integer.parseInt(dao.selectCountVideoFavoritesByMaxMinDate()+"");

        int tabActive = 0;
        if (path.contains("index")){
            if(search != null) {
                list = dao.selectVideoFavoritesByMaxMinDateAndTitle((pageNo - 1) * 12, 12, search);
                sumPage = Integer.parseInt(dao.selectVideoFavoritesByMaxMinDateAndTitle(0, (int) 1e10, search).size()+"");
            }
        }
        /* Xử lý link favouritesUser */
        if(path.contains("favouritesUser")){
            pageNo = request.getParameter("pageNo")!=null?Integer.parseInt(request.getParameter("pageNo")):1;
            tabActive = 1;
            String title = request.getParameter("title")==null?"":request.getParameter("title");
            String sortBy = request.getParameter("selectOption")==null?"":request.getParameter("selectOption");
            if(!sortBy.equals("")){
                if(sortBy.equals("email")) {
                    list = dao.selectUserLikeVideoByEmail(12*(pageNo-1),12,title);
                }
            }else {
                list = dao.selectUserLikeVideoByFullname(12 * (pageNo - 1), 12, title);
            }
            sumPage = Integer.parseInt(dao.selectCountUserLikeVideoByTitle(title)+"");
        }

        /*== xử lý video share ==*/
        else if(path.contains("favouritesShare")){
            pageNo = request.getParameter("pageNo")!=null?Integer.parseInt(request.getParameter("pageNo")):1;
            tabActive = 2;
            System.out.println("chay");
            String title = request.getParameter("search")==null?"":request.getParameter("search");
            int id = -1;
            try {
                id = Integer.parseInt(request.getParameter("selectVideo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            VideoDAO vdao = new VideoDAOImple();
            if(id!=-1){
                request.setAttribute("idVideoSelect",id);
                list = dao.selectUserShareVideoByVideoID((pageNo-1)*12,12,id);
                sumPage = (int) Long.parseLong(dao.selectCountUserShareVideoByVideoId(id)+"");
            }else
                list = dao.selectUserShareVideoByTitle((pageNo-1)*12,12,title);
            request.setAttribute("listVideo",vdao.findAll());
        }

        /*Đếm sum count và phân trang*/
        if (sumPage%12 == 0){
            sumPage = sumPage/12;
        }else{
            sumPage = sumPage/12+1;
        }
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("sumPage",sumPage);
        request.setAttribute("tabActive", tabActive);
        request.setAttribute("list", list);
        request.getRequestDispatcher( "/views/assets/views/AdminReportPage.jsp").forward(request, response);
    }
}