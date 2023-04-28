package com.my_web_app;

import DB.DatabaseConnection;
import com.my_web_app.common.model.Company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 31-Jan-23
 *
 * @author Nayeem
 */
@WebServlet(name = "Init", value = "/Init")
public class Init extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.getSession().setAttribute("company",new Company());
            request.setAttribute("pageName","home");
            request.getRequestDispatcher("index.jsp").forward(request,response);

        } catch (Exception e) {
            System.err.println(e.getMessage());

            // If tables in database are not created then create tables
            DatabaseConnection conn;
            try{
                conn = new DatabaseConnection();
                conn.execute(Utility.getInitialQuery());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
