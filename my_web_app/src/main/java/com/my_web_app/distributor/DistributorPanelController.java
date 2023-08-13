package com.my_web_app.distributor;

import com.my_web_app.common.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DistributorPanel", value = "/DistributorPanel")
public class DistributorPanelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                User user = (User) request.getSession().getAttribute("user");
                request.getRequestDispatcher("/distributor/distributor-panel.jsp").forward(request,response);

            }catch (Exception e){
                e.printStackTrace();
                request.getRequestDispatcher("/error/error.jsp").forward(request,response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
