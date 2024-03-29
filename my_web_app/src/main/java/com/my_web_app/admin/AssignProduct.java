package com.my_web_app.admin;

import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.admin.model.Admin;
import com.my_web_app.common.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AssignProduct", value = "/admin/assign-product")
public class AssignProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                request.setAttribute("role","admin");
                request.getRequestDispatcher("/common/assign-product.jsp").forward(request,response);

            } catch (Exception e) {
                e.printStackTrace();
//                request.setAttribute("error",e);
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                //retrieve the first and last product-id(provided in form) from request parameter
                String firstProduct = request.getParameter("first-product");
                String lastProduct = request.getParameter("last-product");
                User newHolder = new User(Long.parseLong(request.getParameter("new-holder-nid")));

                // check if new holder is valid(under the current holder, admin->distributor)
                if(newHolder.getRole().equals("distributor")){

                    Product product = new Product(firstProduct);
                    ProductBatch productBatch = new ProductBatch(product.getBatchId());
                    ProductMap productMap = new ProductMap(productBatch.getProductCode());

                    // check if the product status is valid(manufactured) to handover by admin
                    if(!Product.hasValidStatus(productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole("admin")))
                    {
                        //if product status is not valid then show error
                        request.setAttribute("error","Unauthorized Access!");
                        request.getRequestDispatcher("/error/error.jsp").forward(request,response);
                    }
                   else{
                        Admin admin = (Admin) request.getSession().getAttribute("admin");
                        // supply product to distributor
                        admin.supplyProduct(productMap,firstProduct,lastProduct,newHolder);

                        response.sendRedirect(request.getServletContext().getContextPath()+"/AdminPanel");

                    }

                }
                else{
                    //if the new holder is not valid then rise error
                    request.setAttribute("error","Invalid Holder!");
                    request.getRequestDispatcher("/error/error.jsp").forward(request,response);
                }


        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }
}
