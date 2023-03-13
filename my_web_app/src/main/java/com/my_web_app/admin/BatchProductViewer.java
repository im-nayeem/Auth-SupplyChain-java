package com.my_web_app.admin;

import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Created on 13-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "BatchProductViewer", value = "/admin/batch-products")
public class BatchProductViewer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ProductBatch productBatch = new ProductBatch(request.getParameter("batchId"));
            ProductMap productMap = new ProductMap(productBatch.getProductCode());
            List<Product> productList = productBatch.getAllProduct();

            request.setAttribute("productMap",productMap);
            request.setAttribute("productBatch",productBatch);
            request.setAttribute("productList",productList);
            request.setAttribute("pageName","allProducts");

            request.getRequestDispatcher("/admin/batch-products.jsp").forward(request,response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
