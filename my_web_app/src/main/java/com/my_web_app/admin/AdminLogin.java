package com.my_web_app.admin;

import com.my_web_app.admin.model.Admin;
import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 10-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "AdminLogin", value = "/AdminLogin")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                request.setAttribute("role","admin");
                request.getRequestDispatcher("/common/login-form.jsp").forward(request,response);

            } catch (Exception e) {
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Admin admin = new Admin(email);
            if(admin.verifyAdmin(password))
            {
                request.getSession().setAttribute("admin",admin);
                response.sendRedirect("AdminPanel");
            }

            else {
                request.setAttribute("role","admin");
                request.setAttribute("error","Incorrect E-mail or Password!");
                request.getRequestDispatcher("/common/login-form.jsp").forward(request,response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
