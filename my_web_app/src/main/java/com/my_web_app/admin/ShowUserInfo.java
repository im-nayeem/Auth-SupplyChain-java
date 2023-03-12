package com.my_web_app.admin;

import com.my_web_app.common.model.User;
import com.my_web_app.distributor.Distributor;
import com.my_web_app.seller.Seller;
import com.my_web_app.supplier.Supplier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 12-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "ShowUserInfo", value = "/admin/user-info")
public class ShowUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(request.getParameter("uid")==null) {

            }
            else {
                long uid = Long.parseLong(request.getParameter("uid"));
                if(User.getRole(uid).equals("seller"))
                    request.setAttribute("user",new Seller(uid));
                else if(User.getRole(uid).equals("supplier"))
                    request.setAttribute("user",new Supplier(uid));
                else if(User.getRole(uid).equals("distributor"))
                    request.setAttribute("user",new Distributor(uid));

                request.getRequestDispatcher("/admin/user-view.jsp").forward(request,response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
