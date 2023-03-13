package com.my_web_app.admin;

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
@WebServlet(name = "AllProductViewer", value = "/admin/all-products")
public class AllProductViewer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            List<ProductMap> productMapList = ProductMap.getAllProductInfoList();
            request.setAttribute("productMapList",productMapList);
            request.setAttribute("pageName","allProducts");
            request.getRequestDispatcher("/admin/all-products.jsp").forward(request,response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
