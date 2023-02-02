package com.example.my_web_app.admin;

import DB.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;

/**
 * Created on 01-Feb-23
 *
 * @author Nayeem
 */
@WebServlet(name = "admin/storeCompanyInfo", value = "/admin/storeCompanyInfo")
public class storeCompanyInfo extends HttpServlet {
    private DatabaseConnection conn;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/admin/companyForm.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            conn=new DatabaseConnection();
            String query="INSERT INTO company_info(name,distributor_code,seller_code,supplier_code) values(?,?,?,?)";
            PreparedStatement pstmt= conn.getPreparedStatement(query);
            pstmt.setString(1,request.getParameter("company-name"));
            pstmt.setString(2,request.getParameter("distributor-code"));
            pstmt.setString(3,request.getParameter("seller-code"));
            pstmt.setString(4,request.getParameter("supplier-code"));

            pstmt.execute();



        }
        catch (Exception e)
        {
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }
}
