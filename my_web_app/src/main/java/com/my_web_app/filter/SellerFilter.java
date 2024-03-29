package com.my_web_app.filter;

import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 10-Mar-23
 *
 * @author Nayeem
 */
@WebFilter(filterName = "SellerFilter",servletNames = {"SellerPanel"},urlPatterns = {"/seller/*"})
public class SellerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //set the requested relative path in session, LogIn servlet will redirect to this path
        req.getSession().setAttribute("servletPath",req.getServletPath()+"?"+req.getQueryString());

        if(req.getSession().getAttribute("user")!=null) {
            User user = (User) req.getSession().getAttribute("user");
            if(user.getRole().equals("seller"))
                chain.doFilter(request, response);
            else
                req.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
        else
            resp.sendRedirect(req.getServletContext().getContextPath()+"/LogIn");

    }
}
