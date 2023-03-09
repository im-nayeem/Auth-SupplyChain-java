package com.my_web_app.common;

import com.my_web_app.common.model.Account;
import com.my_web_app.common.model.User;
import com.my_web_app.distributor.Distributor;
import com.my_web_app.seller.Seller;
import com.my_web_app.seller.SellerPanelController;
import com.my_web_app.supplier.Supplier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 09-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "LogIn", value = "/LogIn")
public class LogIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/common/login-form.jsp").forward(request,response);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");



            Account account = new Account(email);
            //verify account
            if(account.verifyAccount(password)==true)
            {

                String role = User.getRole(account.getUid());
                //if user is seller go to seller panel
                if(role.equals("seller"))
                {
                    request.getSession().setAttribute("user",new Seller(account.getUid()));
                    response.sendRedirect("SellerPanel");
                }
                //if user is distributor go to distributor panel
                else if(role.equals("distributor"))
                {
                    request.getSession().setAttribute("user",new Distributor(account.getUid()));
                    response.sendRedirect("DistributorPanel");
                }
                else if(role.equals("supplier"))
                {
                    request.getSession().setAttribute("user",new Supplier(account.getUid()));
                    response.sendRedirect("SupplierPanel");
                }
                else
                    request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
            else
            {
                request.setAttribute("error","Incorrect e-mail or password! Try with correct information.");
                request.getRequestDispatcher("/common/login-form.jsp").forward(request,response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }
}