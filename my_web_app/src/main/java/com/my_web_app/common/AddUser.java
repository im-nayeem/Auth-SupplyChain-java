package com.my_web_app.common;

import com.my_web_app.Mail;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Account;
import com.my_web_app.common.model.Address;
import com.my_web_app.common.model.Company;
import com.my_web_app.common.model.User;
import com.my_web_app.seller.Seller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/add-user")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("role",request.getParameter("role"));
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

                if(request.getParameter("role").equals("Seller"))
                {
                    Seller seller = new Seller(request, request.getParameter("role"));
                    seller.storeInDatabase();
                    Account account = new Account(seller.getEmail(),"Seller"+ Utility.getVerificationCode());
                    account.storeInDatabase();

                    Company company = new Company();

//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update password
                    Mail mail = new Mail(seller.getEmail());
                    String sub= "New Employee in "+company.getName();
                    String msg = "You are assigned as authorized seller in our company."+
                                 "Visit this link to confirm and update password: "+
                                  request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                                  "/update-password?email="+seller.getEmail()+"&uid="+seller.getNid()+"&pass="+account.getPassword();

                    mail.send(sub,msg);

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
