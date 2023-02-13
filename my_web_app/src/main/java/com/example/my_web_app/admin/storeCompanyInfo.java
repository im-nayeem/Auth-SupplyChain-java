package com.example.my_web_app.admin;

import DB.DatabaseConnection;
import com.example.my_web_app.common.model.Company;

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

            Company company = new Company(request);
            company.storeInDatabase();

            response.sendRedirect("../AdminPanel");


        }
        catch (Exception e)
        {
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }

    }
}
