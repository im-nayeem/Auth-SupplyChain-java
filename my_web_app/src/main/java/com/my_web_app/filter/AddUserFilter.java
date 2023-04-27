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
@WebFilter(filterName = "AddUserFilter",servletNames = {"AddUser"})
public class AddUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getParameter("role").equals("Distributor") && req.getSession().getAttribute("admin")!=null)
            chain.doFilter(request, response);
        else if(req.getSession().getAttribute("user")!=null)
        {
            User user = (User)req.getSession().getAttribute("user");
            String role = req.getParameter("role");
            if((role.equals("DistributorAgent") && user.getRole().equals("distributor")) || (role.equals("Seller") && user.getRole().equals("DistributorAgent")))
                chain.doFilter(request, response);
            else
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
        else
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
    }
}
