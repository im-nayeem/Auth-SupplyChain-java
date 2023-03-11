package com.my_web_app.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 11-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "LogOut", value = "/LogOut")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("admin")!=null)
            request.getSession().removeAttribute("admin");
        if(request.getSession().getAttribute("user")!=null)
            request.getSession().removeAttribute("user");
        response.sendRedirect("./");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
