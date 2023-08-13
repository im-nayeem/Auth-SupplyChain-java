package com.my_web_app.common;

import com.my_web_app.Mail;
import com.my_web_app.Utility;
import com.my_web_app.common.model.*;
import com.my_web_app.distributor.model.Distributor;
import com.my_web_app.seller.model.Seller;
import com.my_web_app.distributorAgent.model.DistributorAgent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/add-user")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //set attribute from url request parameter (e.g, add-user?role=distributor)
            request.setAttribute("role",request.getParameter("role"));
            //retrieve all divisions,districts .... list
            request.setAttribute("divisions", Address.getDivisionList());
            request.setAttribute("districts",Address.getDistrictList());
            request.setAttribute("upazilas",Address.getUpazilaList());
            request.setAttribute("unions",Address.getUnionList());

            request.getRequestDispatcher("/common/user-form.jsp").forward(request,response);
        } catch (Exception e) {
            System.err.println(e);
            request.setAttribute("error",e);
            request.getRequestDispatcher("/error/error.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

                if(request.getParameter("role").equals("seller"))
                {
                    Seller seller = new Seller(request, request.getParameter("role"));

                    // create account for seller with a random password
                    Account account = new Account(seller.getNid(),seller.getEmail(),"Seller"+ Utility.getRandomCode());

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(seller.getEmail());
                    String sub= "New Trader in "+company.getName();
                    String msg = "You are assigned as authorized seller in our company."+
                                 "Visit this link to confirm and update password: "+
                                  request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                                  "/update-password?email="+seller.getEmail()+"&uid="+seller.getNid()+"&pass="+account.getPassword() +
                                    "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);
                    seller.storeInDatabase();
                    account.storeInDatabase();

                    response.sendRedirect("DistributorAgentPanel");

                }
                else if(request.getParameter("role").equals("distributor"))
                {
                    Distributor distributor = new Distributor(request, request.getParameter("role"));

                    // create account for distributor with a random password
                    Account account = new Account(distributor.getNid(),distributor.getEmail(),"Distributor"+ Utility.getRandomCode());

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(distributor.getEmail());
                    String sub= "New Trader in "+company.getName();
                    String msg = "You are assigned as authorized distributor in our company."+
                            "Visit this link to confirm and update password: "+
                            request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                            "/update-password?email="+distributor.getEmail()+"&uid="+distributor.getNid()+"&pass="+account.getPassword() +
                            "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);
                    distributor.storeInDatabase();
                    account.storeInDatabase();

                    response.sendRedirect("AdminPanel");


                }
                else{
                    DistributorAgent distributorAgent = new DistributorAgent(request, request.getParameter("role"));

                    // create account for distributorAgent with a random password
                    Account account = new Account(distributorAgent.getNid(), distributorAgent.getEmail(),"DistributorAgent"+ Utility.getRandomCode());

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(distributorAgent.getEmail());
                    String sub= "New Trader in "+company.getName();
                    String msg = "You are assigned as authorized distributorAgent in our company."+
                            "Visit this link to confirm and update password: "+
                            request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                            "/update-password?email="+ distributorAgent.getEmail()+"&uid="+ distributorAgent.getNid()+"&pass="+account.getPassword() +
                            "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);
                    distributorAgent.storeInDatabase();
                    account.storeInDatabase();

                    response.sendRedirect("DistributorAgentPanel");


                }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("error/error.jsp").forward(request,response);
            throw new RuntimeException(e);
        }
    }
}
