package com.my_web_app.admin;

import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "AddProductBatch", value = "/admin/add-batch")
public class AddProductBatch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("product-code") == null)
            request.getRequestDispatcher("/admin/product-code-form.jsp").forward(request, response);

        request.setAttribute("productMap", new ProductMap(request.getParameter("product-code")));
        request.getRequestDispatcher("/admin/addBatchForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductBatch productBatch=new ProductBatch(request);
            productBatch.storeInDatabase();
            response.sendRedirect("./generate-qr-code?batch="+ URLEncoder.encode(productBatch.getBatchId(),"UTF-8"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.setAttribute("error","Error in adding new batch...");
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);

        }
    }
}
