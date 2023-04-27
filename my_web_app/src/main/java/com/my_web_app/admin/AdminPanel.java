package com.my_web_app.admin;

import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created on 31-Jan-23
 *
 * @author Nayeem
 */
@WebServlet(name = "AdminPanel", value = "/AdminPanel")
public class AdminPanel extends HttpServlet {
    private DatabaseConnection conn;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            // retrieve company data in Company model class
            request.getSession().setAttribute("company",new Company());
            request.setAttribute("pageName","home");
            request.getRequestDispatcher("admin/admin.jsp").forward(request,response);

        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());

            /** If Tables are created but company info is not provided then provide company info **/
            try{
                    //if tables are created but data is not provided
                    response.sendRedirect("admin/storeCompanyInfo");
                }
                catch (Exception ex)
                {
                    System.err.println(ex.getMessage());
                    request.getRequestDispatcher("error/error.jsp").forward(request,response);
                }
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
