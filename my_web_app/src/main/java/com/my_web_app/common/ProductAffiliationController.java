package com.my_web_app.common;

import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Created on 12-Aug-23
 *
 * @author Nayeem
 */
@WebServlet(name = "ProductAffiliationController", value = "/user/product-affiliation")
public class ProductAffiliationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            User user = (User) request.getSession().getAttribute("user");
            List<Product> productList = user.getAffiliatedProducts();

            request.setAttribute("user", user);
            request.setAttribute("productList", productList);
            request.setAttribute("pageName", "my-products");

            if(user.getRole().equals("seller")){
                request.getRequestDispatcher("/seller/affiliated-products.jsp").forward(request,response);
            } else if (user.getRole().equals("distributor")) {
                request.getRequestDispatcher("/distributor/affiliated-products.jsp").forward(request,response);
            } else if (user.getRole().equals("distributorAgent")) {
                request.getRequestDispatcher("/distributorAgent/affiliated-products.jsp").forward(request,response);
            }
        }catch (Exception e){
            System.err.println(e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
