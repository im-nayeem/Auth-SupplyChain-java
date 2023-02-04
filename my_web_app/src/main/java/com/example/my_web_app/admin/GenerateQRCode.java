package com.example.my_web_app.admin;

import com.example.my_web_app.admin.model.ProductInfoGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "GenerateQRCode", value = "/admin/generate-qr-code")
public class GenerateQRCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(request.getParameter("batch")==null)
            {
                //get-all-batch-info
                request.getRequestDispatcher("/admin/all-batch.jsp").forward(request,response);
            }

            ProductInfoGenerator productInfoGenerator = new ProductInfoGenerator();
            productInfoGenerator.generateProductInfo(request.getParameter("batch"));

            request.getRequestDispatcher("/admin/qrCode.jsp").forward(request,response);
        } catch (Exception e) {
//            try {
//                request.getRequestDispatcher("/admin/qrCode.jsp").forward(request,response);
//            } catch (Exception ex) {
                request.setAttribute("error",e+"\n");
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
//            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
