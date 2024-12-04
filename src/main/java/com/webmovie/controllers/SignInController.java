package com.webmovie.controllers;


import com.webmovie.daoimples.UserDAOImple;
import com.webmovie.daos.UserDAO;
import com.webmovie.entities.User;
import com.webmovie.utils.XValidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


/**
 *
 * @author acer
 */
@WebServlet({"/sign-in"})
public class SignInController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/assets/views/Signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin chuẩn bị cập nhật //
        String message = "";
        User usr = new User();
        try {
            BeanUtils.populate(usr, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        message = switch (checkUser(usr)) {
            case -1 -> "Email không đúng định dạng !";
            case -2 -> "Email đã tồn tại !";
            case -3 -> "Username không được chứa kí tự rỗng !";
            case -4 -> "Sai định dạng tên !";
            case -5 -> "Username đã được sử dụng !";
            default -> message;
        };
        if (!message.isEmpty()){
            request.setAttribute("message", message);
            request.getRequestDispatcher("/views/assets/views/Signin.jsp").forward(request, response);
        }else {
            UserDAO dao = new UserDAOImple();
            dao.create(usr);
            request.getRequestDispatcher("/views/assets/views/Login.jsp").forward(request, response);
        }
    }

    private int checkUser(User user){
        UserDAO dao = new UserDAOImple();
        if (!XValidate.email(user.getEmail()))
            return -1;
        if (dao.findByEmail(user.getEmail()) != null)
            return -2;
        for (int i=0 ; i<user.getUsername().length() ;i++){
            if (user.getUsername().charAt(i) == ' '){
                return -3;
            }
        }
        if (!XValidate.checkName(user.getFullname()))
            return -4;
        if(dao.checkValidUsername(user.getUsername()))
            return -5;
        return 1;
    }
}

