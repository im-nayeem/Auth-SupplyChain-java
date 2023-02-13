package com.my_web_app.common;

import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

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
               }
               Product product = new Product(request.getParameter("pid"));
               ProductBatch productBatch = new ProductBatch(product.getBatchId());
               ProductMap productMap = new ProductMap(productBatch.getProductCode());
               request.setAttribute("product",product);
               request.setAttribute("productBatch",productBatch);
               request.setAttribute("productMap",productMap);

               request.getRequestDispatcher("/common/view-product.jsp").forward(request,response);
           } catch (Exception e) {
               request.setAttribute("error",e);
               request.getRequestDispatcher("/error/error.jsp").forward(request,response);
               throw new RuntimeException(e);
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
