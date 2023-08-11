package com.my_web_app.admin;

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
@WebServlet(name = "admin/Release-Product", value = "/admin/release-product")
public class ReleaseProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/releaseProductForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductMap productMap = new ProductMap(request);
            productMap.storeInDatabase();

            String redirectUrl = "add-batch?product-code=" + URLEncoder.encode(productMap.getProductCode(),"UTF-8");

            response.sendRedirect(redirectUrl);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);

        }

    }
}
