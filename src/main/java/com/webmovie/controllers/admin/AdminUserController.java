package com.webmovie.controllers.admin;


import com.webmovie.daoimples.UserDAOImple;
import com.webmovie.daos.UserDAO;
import com.webmovie.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet({"/admin/user/index",
        "/admin/user/edit/*",
        "/admin/user/create",
        "/admin/user/update/*",
        "/admin/user/delete",
        "/admin/user/reset",
        "/admin/user/search/*",
})
public class AdminUserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Biến dùng chung và chỉ setAttribute ở cuối trang servlet //
        long maxRecordData = 0;
        int sumCountPage = -1;
        int pageActive = 1;
        int maxPageNo = 10;
        boolean isEdit = true;
        boolean isSuccess = false;

        // Dùng để đưa biến lên phạm vi session và lấy thuộc tính của nó //
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        UserDAO dao = new UserDAOImple();
        List<User> list = null;
        User usr = new User();
        try {
            BeanUtils.populate(usr, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /*Trang đầu tiên khi mở user Controller lên*/
        if (path.contains("index")) {
            maxRecordData = (long) dao.selectCountByName("","");
            list = dao.findAll(0,10);
            isEdit = false;
        }
        /*Xử lý path edit*/
        else if (path.contains("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String role = request.getParameter("role");
            String search = request.getParameter("search");
            boolean admin;
            int pageNo = Integer.parseInt(session.getAttribute("pageActive")+"");
            usr = dao.findById(id);
            if(!role.equals("")){
                admin = Boolean.parseBoolean(role);
                list = dao.findByEmail((pageNo-1)*maxPageNo, maxPageNo, search.trim(), admin);
            }else{
                list = dao.findByEmail((pageNo-1)*maxPageNo, maxPageNo, search.trim());
            }
            maxRecordData =dao.selectCountByName(search,role);
            pageActive = pageNo;
            isEdit = true;
        }
        /*Xử lý path create*/
        else if (path.contains("create")) {
            if (dao.findByEmail(request.getParameter("email")) == null) {
                usr.setId(null);
                dao.create(usr);
                request.setAttribute("message", "Tạo mới thành công người dùng id: " + usr.getId() + " || Name: " + usr.getFullname() + " || Quyền: " + (usr.getAdmin() ? "Admin" : "User"));
                usr = new User();
                isSuccess = true;
                maxRecordData =(long) dao.selectCountByName("","");
            } else {
                request.setAttribute("message", "Email đã tồn tại !");
            }
        }
        /*Xử lý path update*/
        else if (path.contains("update")) {
            if (dao.findValidEmail(usr) == null) {
                dao.update(usr);
                request.setAttribute("message", "Cập nhật thành công người dùng id: " + usr.getId() + " || Name: " + usr.getFullname());
                isSuccess = true;
            } else {
                request.setAttribute("message", "Email đã tồn tại !");
            }
            maxRecordData =(long) dao.selectCountByName("","");
        }
        /*Xử lý path delete*/
        else if (path.contains("delete")) {
            if (dao.deleteById(usr.getId()) == null) {
                request.setAttribute("message", " Không thể xóa user này !");
            } else {
                request.setAttribute("message", " Xóa thành công người dùng id: " + usr.getId() + " || Name: " + usr.getFullname());
                isSuccess = true;
                usr = new User();
            }
            maxRecordData =(long) dao.selectCountByName("","");
        }
        /*Xử lý path reset*/
        else if (path.contains("reset")) {
            usr = new User();
            usr.setAdmin(false);
            maxRecordData =(long) dao.selectCountByName("","");
        }
        /*Xử lý path search*/
        else if (path.contains("search")) {
            int pageNo=1;
            boolean admin=false;
            String search = request.getParameter("search");
            String role = request.getParameter("role");
            try {
                pageNo = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
            }
            if(!role.equals("")){
                admin = Boolean.parseBoolean(role);
                list = dao.findByEmail((pageNo-1)*maxPageNo, maxPageNo, search.trim(), admin);
            }else{
                list = dao.findByEmail((pageNo-1)*maxPageNo, maxPageNo, search.trim());
            }
            maxRecordData =dao.selectCountByName(search,role);
            pageActive = pageNo;
            isEdit = false;
        }

        // Kiểm tra list và trả về trang trước đó đang đứng với dữ liệu tương ứng //
        if (list == null) {
            int pageNo =Integer.parseInt(session.getAttribute("pageActive")+"");
            pageActive = pageNo;
            list = dao.findAll((pageNo-1)*10, 10);
        }
        if (usr.getId() == null) {
            usr.setAdmin(false);
        }
        if (maxRecordData == -1) {
            sumCountPage = 0;
        }
        if (maxRecordData < 10) {
            sumCountPage = 1;
        } else {
            sumCountPage = (int) Math.ceil((maxRecordData + 0.0) / 10);
        }

        // Chuyển đổi tab bằng edit Nếu edit = true --> UserEdit Tab //
        request.setAttribute("edit",isEdit);
        // Thông báo thông tin cho người dùng thấy tình trạng thực hiện hành vi //
        request.setAttribute("success", isSuccess);
        request.setAttribute("item", usr);
        request.getSession().setAttribute("pageActive", pageActive);
        request.setAttribute("sumCountPage", sumCountPage);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/assets/views/AdminUserPage.jsp").forward(request, response);
    }
}

