package com.webmovie.controllers.admin;


import com.webmovie.daoimples.VideoDAOImple;
import com.webmovie.daos.VideoDAO;
import com.webmovie.entities.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@MultipartConfig()
@WebServlet({"/admin/videos/index/*",
        "/admin/videos/create/*",
        "/admin/videos/update/*",
        "/admin/videos/delete/*",
        "/admin/videos/edit/*",
        "/admin/videos/reset",
        "/admin/videos/upload",
})
public class AdminVideoController extends HttpServlet {

    Video video = new Video();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        String path = request.getServletPath();
        VideoDAO dao = new VideoDAOImple();
        List<Video> list = null;
        int sumPage = 1;
        int pageNo = 1;
        boolean isEdit = false;

        /* Đăng kí ngày thàng với beanutil*/
        DateTimeConverter dtc = new DateConverter(new Date());
        dtc.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dtc, Date.class);

        try {
            BeanUtils.populate(video, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        /*Xử lý index */
        if (path.contains("index")) {
            pageNo = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            String search = request.getParameter("search") == null ? "" : request.getParameter("search");
            String status = request.getParameter("status") != null ? request.getParameter("status"):"";
            if (status.isEmpty()) {
                list = dao.findAllByTitleOrStatus((pageNo - 1) * 10, 10, search);
                sumPage = (int) Long.parseLong(dao.findAllCountByTitleOrStatus(search) + "");
            } else {
                list = dao.findAllByTitleOrStatus((pageNo - 1) * 10, 10, search, Boolean.parseBoolean(status));
                sumPage = (int) Long.parseLong(dao.findAllCountByTitleOrStatus(search, Boolean.valueOf(status)) + "");
                request.setAttribute("status", Boolean.valueOf(status));
            }
        }
        /*== Xử lý edit video ==*/
        else if (path.contains("edit")) {
            isEdit = true;
            video.setActive(true);
            Integer id = Integer.parseInt(request.getPathInfo().substring(1));
            request.setAttribute("video", dao.findById(id));
        }

        /*== Xử lý upload ảnh ==*/
        else if (path.contains("upload")) {
            isEdit = true;
            video.setPoster(request.getParameter("poster"));
            video.setImagePoster(request.getParameter("imagePoster"));
            Collection<Part> parts = request.getParts();
            try {
                for(Part part : parts) {
                    if(part.getName().equals("photoBanner")) {
                        if(!part.getSubmittedFileName().isEmpty()){
                            video.setPoster(part.getSubmittedFileName());
                            upload(request,part,"/views/assets/images/banners");
                        }
                    }else if(part.getName().equals("photoPoster")) {
                        if(!part.getSubmittedFileName().isEmpty()){
                            video.setImagePoster(part.getSubmittedFileName());
                            upload(request,part,"/views/assets/images/products");
                        }
                    }
                }
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
            request.setAttribute("video", video);
        }

        /*== Xử lý tạo video ==*/
        else if (path.contains("create")) {
            video.setId(null);
            isEdit = true;
            pageNo = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            if (dao.create(video) == null) {
                request.setAttribute("message", "Tạo mới video thất bại.");
                request.setAttribute("success", false);
                request.setAttribute("video", video);
            } else {
                request.setAttribute("success", true);
                request.setAttribute("message", "Tạo mới video thành công.");
            }
        }

        /*== Xử lý reset form ==*/
        else if (path.contains("reset")) {
            isEdit = true;
            video = new Video();
            video.setActive(true);
            video.setPostDate(new Date());
            request.setAttribute("video", video);
        }

        /*== Xử lý update ==*/
        else if(path.contains("update")) {
            isEdit = true;
            if(dao.update(video) == null){
                request.setAttribute("message", "Cập nhật video thất bại.");
                request.setAttribute("success", false);
                request.setAttribute("video", video);
            }else{
                request.setAttribute("success", true);
                request.setAttribute("message", "Cập nhật video thành công.");
                request.setAttribute("video", video);
            }
        }

        /*== Xử lý delete ==*/
        else if(path.contains("delete")) {
            isEdit = true;
            if(dao.deleteById(video.getId()) == null){
                request.setAttribute("message", "Xóa video thất bại.");
                request.setAttribute("success", false);
            }else{
                request.setAttribute("success", true);
                request.setAttribute("message", "Xóa video thành công.");
            }
        }

        /*  Check list null */
        if (list == null) {
            pageNo = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            list = dao.findAllByTitleOrStatus((pageNo - 1) * 10, 10, "");
            sumPage = (int) Long.parseLong(dao.findAllCountByTitleOrStatus("") + "");
        }


        sumPage = sumPage % 10 == 0 ? sumPage / 10 : sumPage / 10 + 1;
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("sumPage", sumPage);
        request.setAttribute("isEdit", isEdit);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/assets/views/AdminVideoPage.jsp").forward(request, response);
    }


    private String upload(HttpServletRequest request, Part part, String folder) {
        String path = request.getServletContext().getRealPath(folder);
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        String filePath = path + File.separator+part.getSubmittedFileName();
        try {
            part.write(filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}