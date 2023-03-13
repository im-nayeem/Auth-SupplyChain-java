package com.my_web_app.admin;

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
@WebServlet(name = "BatchViewer", value = "/admin/all-batch")
public class BatchViewer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ProductMap productMap = new ProductMap(request.getParameter("productCode"));
            List<ProductBatch> batchList = new ProductBatch().getAllBatch(request.getParameter("productCode"));

            request.setAttribute("productMap",productMap);
            request.setAttribute("batchList",batchList);
            request.setAttribute("pageName","allProducts");

            request.getRequestDispatcher("/admin/all-batch.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
