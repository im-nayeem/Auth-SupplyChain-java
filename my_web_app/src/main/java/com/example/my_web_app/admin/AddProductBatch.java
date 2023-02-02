package com.example.my_web_app.admin;

import com.example.my_web_app.admin.model.ProductBatch;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "AddProductBatch", value = "/admin/add-batch")
public class AddProductBatch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/addBatchForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductBatch productBatch=new ProductBatch(request);
            productBatch.storeInDatabase();
        } catch (Exception e) {
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);

        }
    }
}
