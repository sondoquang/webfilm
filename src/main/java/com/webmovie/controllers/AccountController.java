package com.webmovie.controllers;

import com.webmovie.daoimples.FavouriteDAOImple;
import com.webmovie.daoimples.UserDAOImple;
import com.webmovie.daos.FavouriteDAO;
import com.webmovie.daos.UserDAO;
import com.webmovie.entities.User;
import com.webmovie.entities.Video;
import com.webmovie.utils.XValidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author acer
 */
@WebServlet({
        "/account",
        "/account/change-password",
        "/account/edit-profile"
})
public class AccountController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path  = request.getServletPath();
        FavouriteDAO fdao = new FavouriteDAOImple();
        UserDAO dao = new UserDAOImple();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user") ==null?null:(User) session.getAttribute("user");
        String message = "";
        List<Video> listFavouriteUser = user != null?fdao.getVideoFavouritesByUser(user.getId()):null;
        List<Video> listTopFavourite = fdao.getTop10VideosFavourite(10);

        // Lấy thông tin chuẩn bị cập nhật //
        User usr = new User();
        try {
            BeanUtils.populate(usr, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        /*Xử lý phẩn cập nhật mật khẩu profile user online*/
        if (path.contains("change-password")){
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            if (!newPassword.equals(confirmPassword)){
                request.setAttribute("message", "Xác nhận mật khẩu chưa trùng khớp !!");
            }else{
                user.setPassword(newPassword);
                dao.update(user);
                request.setAttribute("message", "Cập nhật thành công !!");
                request.getSession().setAttribute("user", user);
            }
        }

        /*Xử lý phẩn cập nhật profile user online*/
        else if (path.contains("edit-profile")){
            message = switch (validateEmail(usr)) {
                case -1 -> "Email không đúng định dạng !";
                case -2 -> "Email đã tồn tại !";
                case -3 -> "Tên không đúng định dạng !";
                default -> message;
            };
            if (!message.isEmpty()){
                request.setAttribute("message", message);
            }else{
                usr.setId(user.getId());
                dao.update(usr);
                request.setAttribute("message", message);
                request.getSession().setAttribute("user", usr);
            }
        }
        //Chuyển đến trang userAccount //
        request.setAttribute("videoFavourites",fdao.getVideoFavouritesByUser(user.getId()));
        request.setAttribute("list",listFavouriteUser);
        request.setAttribute("listTop10Videos", listTopFavourite);
        request.getRequestDispatcher("/views/assets/views/UserAccountPage.jsp").forward(request, response);
    }




    private int validateEmail(User user) {
        UserDAO dao = new UserDAOImple();
        if (!XValidate.email(user.getEmail()))
            return -1;
        if (dao.findValidEmail(user) != null)
            return -2;
        if (!XValidate.checkName(user.getFullname())){
            return -3;
        }
        return 1;
    }

    private int checkUser(User user){
        UserDAO dao = new UserDAOImple();
        validateEmail(user);
        // check kis tu rong cho username //
        for(int i=0 ; i<user.getUsername().length() ;i++){
            if (user.getUsername().charAt(i) == ' '){
                return -3;
            }
        }
        if (!XValidate.checkName(user.getFullname()))
            return -4;
        return 1;
    }
}
