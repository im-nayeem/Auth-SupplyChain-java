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
@WebServlet(name = "AccountProfile", value = "/user-profile")
public class AccountProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName","profile");
        request.getRequestDispatcher("/common/account-profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
