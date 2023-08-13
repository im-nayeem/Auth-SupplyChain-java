package com.my_web_app.common;

import com.my_web_app.common.model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateOTP", value = "/update-password")
public class UpdateOTP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           Account account = new Account(request.getParameter("email"));
           if( account.verifyAccount(request.getParameter("pass")) )
           {
               request.setAttribute("email",request.getParameter("email"));
               request.setAttribute("uid",request.getParameter("uid"));
               request.getRequestDispatcher("/common/update-password.jsp").forward(request,response);
           }
           request.setAttribute("error","Invalid!");
           request.getRequestDispatcher("/error/error.jsp").forward(request,response);
       }catch (Exception e){
           request.setAttribute("error",e);
           request.getRequestDispatcher("/error/error.jsp").forward(request,response);
       }

   }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            long uid = Long.parseLong(request.getParameter("uid"));
            Account account = new Account(uid,email,password);
            /** Update Password **/
            account.updatePassword();

            response.sendRedirect(request.getServletContext().getContextPath()+"/LogIn");

        } catch (Exception e) {
            e.printStackTrace();
//            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
