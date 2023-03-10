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
            String pid = request.getParameter("pid");
            //if pid is null then sell product in range
            if(pid==null)
                request.getRequestDispatcher("/seller/sell-products.jsp").forward(request,response);

            Product product = new Product(pid);
            ProductMap productMap = new ProductMap(Utility.getCode(product.getProductId()));
            request.setAttribute("product",product);
            request.setAttribute("productMap",productMap);

            // if requested by scanned QR code then sell ony one product
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

            response.sendRedirect(request.getServletContext().getContextPath()+"/SellerPanel");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
