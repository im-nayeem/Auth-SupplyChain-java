package com.my_web_app.common;

import com.my_web_app.SecurePassword;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdatePassword", value = "/update-password")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           Account account = new Account(request.getParameter("email"));
           if( account.verifyAccount(request.getParameter("pass")) )
           {
               request.setAttribute("email",request.getParameter("email"));
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

    }
}
