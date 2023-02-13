package com.example.my_web_app.common;

import com.example.my_web_app.common.model.Address;
import com.example.my_web_app.common.model.Seller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/add-user")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("user",request.getParameter("user"));
            request.setAttribute("divisions", Address.getDivisionList());
            request.setAttribute("districts",Address.getDistrictList());
            request.setAttribute("upazilas",Address.getUpazilaList());
            request.setAttribute("unions",Address.getUnionList());
            request.getRequestDispatcher("/common/user-form.jsp").forward(request,response);
        } catch (Exception e) {
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("Entered");
            System.out.println(request.getParameter("user"));

                if(request.getParameter("user").equals("Seller"))
                {
                    Seller seller = new Seller(request);
                    seller.storeInDatabase();

                }
                else if(request.getParameter("user") == "Distributor")
                {

                }
                else{

                }

        } catch (Exception e) {
            request.setAttribute("error",e);
            request.getRequestDispatcher("error/error.jsp").forward(request,response);
            throw new RuntimeException(e);
        }
    }
}
