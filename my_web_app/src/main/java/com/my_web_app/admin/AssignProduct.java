package com.my_web_app.admin;

import com.my_web_app.Utility;
import com.my_web_app.common.model.Account;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AssignProduct", value = "/assign-product")
public class AssignProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                request.setAttribute("role",request.getParameter("role"));
                request.getRequestDispatcher("/common/assign-product.jsp").forward(request,response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error",e);
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Account newHolder = new Account(Long.parseLong(request.getParameter("new-holder-nid")));

            Product product = new Product(request.getParameter("first-product"));
            ProductBatch productBatch = new ProductBatch(product.getBatchId());
            ProductMap productMap = new ProductMap(productBatch.getProductCode());

            /**
             * Check if current holder is admin. In product table last_holder is set null by default. That means this product is just produced and only admin can access to these products.
             */
            if(request.getParameter("uid")==null && product.getStatus().equals("produced") && newHolder.getRole()!=null)
            {
                Product.updateProductHolder(productMap.getTableName(),request.getParameter("first-product"),request.getParameter("last-product"),newHolder.getUid());
            }
            Account currentHolder = new Account(Long.parseLong(request.getParameter("uid")));

//            if(request.getParameter("uid"))

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }
}
