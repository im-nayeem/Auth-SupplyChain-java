package com.my_web_app.common;

import com.my_web_app.common.model.*;
import com.my_web_app.seller.model.Seller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 06-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "ViewProduct", value = "/view-product")
public class ViewProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           try{
               if(request.getParameter("pid")==null)
               {
                   //dispatch to form to collect product id
                   request.getRequestDispatcher("/common/view-product-form.jsp").forward(request,response);
               }
               else{
                   Company company = new Company();
                   Product product = new Product(request.getParameter("pid"));
                   ProductBatch productBatch = new ProductBatch(product.getBatchId());
                   ProductMap productMap = new ProductMap(productBatch.getProductCode());

                   request.setAttribute("company",company);
                   request.setAttribute("product",product);
                   request.setAttribute("productBatch",productBatch);
                   request.setAttribute("productMap",productMap);

                   if(product.getStatus().equals("sold"))
                       request.setAttribute("seller",new Seller(product.getLastHolder()));

                   request.setAttribute("lastHolder",new User(product.getLastHolder()));

                   request.getRequestDispatcher("/common/view-product.jsp").forward(request,response);
               }
           } catch (Exception e) {
               e.printStackTrace();
               request.getRequestDispatcher("/error/error.jsp").forward(request,response);
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try{
//            response.sendRedirect("view-product?pid="+request.getParameter("pid"));
//        } catch (Exception e) {
//            request.setAttribute("error",e);
//            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
//        }
    }
}
