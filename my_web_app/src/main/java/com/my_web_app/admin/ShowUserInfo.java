package com.my_web_app.admin;

import com.my_web_app.admin.model.TradersDAO;
import com.my_web_app.common.model.User;
import com.my_web_app.distributor.Distributor;
import com.my_web_app.seller.Seller;
import com.my_web_app.distributorAgent.DistributorAgent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Created on 12-Mar-23
 *
 * @author Nayeem
 */
@WebServlet(name = "ShowUserInfo", value = "/admin/user-info")
public class ShowUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(request.getParameter("uid") == null) {
                TradersDAO tradersDAO = new TradersDAO();
                request.setAttribute("traders", tradersDAO.getTraders());
                request.setAttribute("pageName","userInfo");
                request.getRequestDispatcher("/admin/all-traders.jsp").forward(request, response);
            }
            else {
                long uid = Long.parseLong(request.getParameter("uid"));
                if(User.getRole(uid).equals("seller"))
                    request.setAttribute("user",new Seller(uid));
                else if(User.getRole(uid).equals("distributorAgent"))
                    request.setAttribute("user",new DistributorAgent(uid));
                else if(User.getRole(uid).equals("distributor"))
                    request.setAttribute("user",new Distributor(uid));
                request.setAttribute("pageName","userInfo");
                request.getRequestDispatcher("/admin/user-view.jsp").forward(request,response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
