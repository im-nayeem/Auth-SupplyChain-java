package com.my_web_app.distributor;

import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DistributeProduct", value = "/distributor/distribute-product")
public class DistributeProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("role","distributor");
            request.getRequestDispatcher("/common/assign-product.jsp").forward(request,response);
        }
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User newHolder = new User(Long.parseLong(request.getParameter("new-holder-nid")));

            // check if new holder is valid
            if(newHolder.getRole().equals("supplier") || newHolder.getRole().equals("seller")){

                Product product = new Product(request.getParameter("first-product"));
                ProductBatch productBatch = new ProductBatch(product.getBatchId());
                ProductMap productMap = new ProductMap(productBatch.getProductCode());

                // update product holder
                Product.updateProductHolder(productMap.getTableName(),request.getParameter("first-product"),request.getParameter("last-product"),newHolder.getNid());

                // update product status
                Product.updateProductStatus(productMap.getTableName(),request.getParameter("first-product"),request.getParameter("last-product"), Utility.productStatusByRole(newHolder.getRole()));
            }

            response.sendRedirect("../DistributorPanel");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
