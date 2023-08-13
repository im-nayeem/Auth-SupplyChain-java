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
           e.printStackTrace();

            /** If company info is not provided then provide company info for the first time **/
            try{
                    response.sendRedirect("admin/storeCompanyInfo");
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
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
