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
            conn=new DatabaseConnection();
            ResultSet rs= conn.executeQuery("Select COUNT(*) as n from company_info;");
            if(rs.next())
            {
//                check if company info is initialized
                if(rs.getInt("n")==0)
                {
                    throw new RuntimeException("No Data!");
                }

            }
            request.getSession().setAttribute("company",new Company());
            request.setAttribute("pageName","home");
            request.getRequestDispatcher("admin/admin.jsp").forward(request,response);



        }
        catch (Exception e)
        {
//            request.setAttribute("error",e);
//            request.getRequestDispatcher("error/error.jsp").forward(request,response);

            /** If Tables are not created then create tables and fill up form **/
            try{
                conn.execute(Utility.getInitialQuery());
                response.sendRedirect("admin/storeCompanyInfo");
            } catch (Exception ex) {
                try {
                    ex.notify();

                    //if tables are created but data is not provided
                    response.sendRedirect("admin/storeCompanyInfo");
                }
                catch (Exception e1)
                {
                    request.setAttribute("error",e+"\n"+ex);
                    request.getRequestDispatcher("error/error.jsp").forward(request,response);
                }
            }
            finally {
                try{
                    conn.close();
                }
                catch (RuntimeException exception){}
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
