package com.my_web_app.seller;

import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller to sell products by seller
 */
@WebServlet(name = "SellProduct", value = "/seller/sell-product")
public class SellProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            request.setAttribute("role","seller");

            //if pid is null or pid is not provided(there is no request parameter) then sell product in range
            if(!request.getParameterNames().hasMoreElements())
                request.getRequestDispatcher("/seller/sell-products.jsp").forward(request,response);

            //if pid is provided in request(requested by scanning QR code) then sell the product of productID=pid
             else{
                Product product = new Product(request.getParameter("pid"));
                ProductMap productMap = new ProductMap(Utility.getCode(product.getProductId()));
                request.setAttribute("product",product);
                request.setAttribute("productMap",productMap);
                User user = (User) request.getSession().getAttribute("user");

                //check if seller has access to the product and is not sold
                if(!User.hasAccessToProduct(user.getNid(),productMap.getTableName(),product.getProductId(),product.getProductId()) || !Product.hasValidStatus(productMap.getTableName(), product.getProductId(), product.getProductId(), Utility.productStatusByRole("seller")))
                {
                    //if seller has no access to the product or the product is already sold the shpw error
                    request.setAttribute("error","Unauthorized Access!");
                    request.getRequestDispatcher("/error/error.jsp").forward(request,response);
                }
                else{
                    //If seller has access then provide interface to mark as sold
                    request.getRequestDispatcher("/seller/sell-product.jsp").forward(request,response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //retrieve the first and last product-id(provided in form) from request parameter
            String firstProduct = request.getParameter("first-product");
            String lastProduct = request.getParameter("last-product");

            //if last product is null then sell only the first product,assign lastProduct=firstProduct
            if(lastProduct==null)
                lastProduct = firstProduct;

            Product product = new Product(firstProduct);
            ProductBatch productBatch = new ProductBatch(product.getBatchId());
            ProductMap productMap = new ProductMap(productBatch.getProductCode());

            User user = (User) request.getSession().getAttribute("user");

            // check if the product status is distributed and the seller is assigned with these products
            if(!User.hasAccessToProduct(user.getNid(),productMap.getTableName(),firstProduct,lastProduct) || !Product.hasValidStatus(productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole("seller")))
            {
                // if not then show error
                request.setAttribute("error","Unauthorized Access!");
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
            else{
                Seller seller = new Seller(user.getNid());
                seller.sellProduct(productMap, firstProduct, lastProduct);
                response.sendRedirect(request.getServletContext().getContextPath()+"/SellerPanel");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }
}
