package com.my_web_app.admin.model;

import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 13-Aug-23
 *
 * @author Nayeem
 */
@WebServlet(name = "UpdatePassword", value = "/admin/update-password")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/admin/update-password.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String oldPassword = request.getParameter("old-password");
            String newPassword = request.getParameter("password");

            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(admin.verifyAdmin(oldPassword)){
                admin.updatePassword(newPassword);
            }
            response.sendRedirect(request.getContextPath()+"/AdminPanel");

        }catch (Exception e){
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        }
    }
}
