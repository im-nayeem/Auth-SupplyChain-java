package com.my_web_app.seller;

import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SellProduct", value = "/seller/sell-product")
public class SellProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("role","seller");
            request.setAttribute("pid","AP1");
            request.getRequestDispatcher("/seller/sell-product.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            Seller seller = new Seller(123456789);

            String firstProduct = request.getParameter("first-product");
            String lastProduct = request.getParameter("last-product");

            if(lastProduct==null)
                lastProduct = firstProduct;




            Product product = new Product(firstProduct);
            ProductBatch productBatch = new ProductBatch(product.getBatchId());
            ProductMap productMap = new ProductMap(productBatch.getProductCode());


            // update product status
            Product.updateProductStatus(productMap.getTableName(),firstProduct,lastProduct,Utility.getProductStatus("customer"));
            Product.updateSoldDate(productMap.getTableName(),firstProduct,lastProduct);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
