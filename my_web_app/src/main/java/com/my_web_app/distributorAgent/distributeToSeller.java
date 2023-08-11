package com.my_web_app.distributorAgent;

import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DistributeToSeller", value = "/distributorAgent/distribute-to-seller")
public class distributeToSeller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("role","distributorAgent");
            request.getRequestDispatcher("/common/assign-product.jsp").forward(request,response);
        }
        catch (Exception e) {
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
            User newHolder = new User(Long.parseLong(request.getParameter("new-holder-nid")));

            // check if new holder is valid(under the current holder, admin->distributor->distributorAgent->seller)
            if(newHolder.getRole().equals("seller")){

                Product product = new Product(firstProduct);
                ProductBatch productBatch = new ProductBatch(product.getBatchId());
                ProductMap productMap = new ProductMap(productBatch.getProductCode());
                User user = (User) request.getSession().getAttribute("user");

                // check if the product status is distributing and the distributorAgent is assigned with these products
                if(!User.hasAccessToProduct(user.getNid(),productMap.getTableName(),firstProduct,lastProduct) || !Product
                .hasValidStatus(productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole("distributorAgent")))
                {
                    //if not then show error
                    request.setAttribute("error","Unauthorized Access!");
                    request.getRequestDispatcher("/error/error.jsp").forward(request,response);
                }
                else{

                    new DistributorAgent(user.getNid()).
                            distributeProductToSeller(productMap, firstProduct, lastProduct, newHolder);

                    response.sendRedirect(request.getServletContext().getContextPath()+"/DistributorAgentPanel");
                }
            }
            else{
                //if the new holder is not valid then rise error
                request.setAttribute("error","Invalid Holder!");
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }



        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

}
