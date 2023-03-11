package com.my_web_app.admin;

import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.admin.model.ProductInfoGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "GenerateQRCode", value = "/admin/generate-qr-code")
public class GenerateQRCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("batch") == null) {
                List<ProductBatch>batchList = new ProductBatch().getAllBatch();
                request.setAttribute("batchList",batchList);
                request.setAttribute("pageName","generateQr");
                request.getRequestDispatcher("/admin/all-batch.jsp").forward(request, response);
            }

            //if product info for this batch is not generated,generate them first
            ProductInfoGenerator productInfoGenerator = new ProductInfoGenerator();
            productInfoGenerator.generateProductInfo(request.getParameter("batch"));

            //get list of all products
            List<Product> productList = new ProductBatch(request.getParameter("batch")).getAllProduct();

            request.setAttribute("productList",productList);

            //dispatch to qrCode.jsp to generate qr code
            request.getRequestDispatcher("/admin/qrCode.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //if product info are already generated then generate qr code
                List<Product> productList = new ProductBatch(request.getParameter("batch")).getAllProduct();

                request.setAttribute("productList",productList);
                request.getRequestDispatcher("/admin/qrCode.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
                request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            }

            }

        }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

        }
    }

