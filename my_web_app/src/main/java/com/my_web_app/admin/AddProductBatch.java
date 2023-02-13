package com.my_web_app.admin;

import com.my_web_app.common.model.ProductBatch;

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
        request.setAttribute("p_code",request.getParameter("p_code"));
        request.getRequestDispatcher("/admin/addBatchForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductBatch productBatch=new ProductBatch(request);
            productBatch.storeInDatabase();
            response.sendRedirect("./generate-qr-code?batch="+ URLEncoder.encode(productBatch.getBatchId(),"UTF-8"));
        } catch (Exception e) {
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);

        }
    }
}
