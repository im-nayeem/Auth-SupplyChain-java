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
@WebFilter(filterName = "SupplierFilter",servletNames = {"DistributorPanel"},urlPatterns = {"/supplier/*"})
public class SupplierFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getSession().getAttribute("user")!=null) {
            User user = (User) req.getSession().getAttribute("user");
            if(user.getRole().equals("supplier"))
                chain.doFilter(request, response);
            else
                req.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
        else
            resp.sendRedirect(req.getServletContext().getContextPath()+"/LogIn");
    }
}
