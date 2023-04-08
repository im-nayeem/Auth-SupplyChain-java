package com.my_web_app.filter;

import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 09-Mar-23
 *
 * @author Nayeem
 */
@WebFilter(filterName = "AdminFilter",servletNames = {"AdminPanel"},urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getSession().getAttribute("admin")!=null){
            chain.doFilter(req, resp);
        }
        else
        {
            resp.sendRedirect(request.getServletContext().getContextPath()+"/AdminLogin");
        }
    }
}
