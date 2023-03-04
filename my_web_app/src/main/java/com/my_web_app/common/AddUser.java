package com.my_web_app.common;

import com.my_web_app.Mail;
import com.my_web_app.Utility;
import com.my_web_app.common.model.*;
import com.my_web_app.distributor.Distributor;
import com.my_web_app.seller.Seller;
import com.my_web_app.supplier.Supplier;

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

                    // create account for seller with a random password
                    Account account = new Account(seller.getNid(),seller.getEmail(),"Seller"+ Utility.getRandomCode());
                    account.storeInDatabase();

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(seller.getEmail());
                    String sub= "New Employee in "+company.getName();
                    String msg = "You are assigned as authorized seller in our company."+
                                 "Visit this link to confirm and update password: "+
                                  request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                                  "/update-password?email="+seller.getEmail()+"&uid="+seller.getNid()+"&pass="+account.getPassword() +
                                    "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);

                    response.sendRedirect("SupplierPanel");

                }
                else if(request.getParameter("role").equals("Distributor"))
                {
                    Distributor distributor = new Distributor(request, request.getParameter("role"));
                    distributor.storeInDatabase();

                    // create account for distributor with a random password
                    Account account = new Account(distributor.getNid(),distributor.getEmail(),"Distributor"+ Utility.getRandomCode());
                    account.storeInDatabase();

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(distributor.getEmail());
                    String sub= "New Employee in "+company.getName();
                    String msg = "You are assigned as authorized distributor in our company."+
                            "Visit this link to confirm and update password: "+
                            request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                            "/update-password?email="+distributor.getEmail()+"&uid="+distributor.getNid()+"&pass="+account.getPassword() +
                            "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);
                    response.sendRedirect("AdminPanel");


                }
                else{
                    Supplier supplier = new Supplier(request, request.getParameter("role"));
                    supplier.storeInDatabase();

                    // create account for supplier with a random password
                    Account account = new Account(supplier.getNid(),supplier.getEmail(),"Supplier"+ Utility.getRandomCode());
                    account.storeInDatabase();

                    Company company = new Company();
//                    Company company = (Company) request.getSession().getAttribute("company");

                    // Send mail to confirm and update the password(randomly generated above)
                    Mail mail = new Mail(supplier.getEmail());
                    String sub= "New Employee in "+company.getName();
                    String msg = "You are assigned as authorized supplier in our company."+
                            "Visit this link to confirm and update password: "+
                            request.getScheme() + "://" + Utility.getIp() + ":" + request.getServerPort() + request.getContextPath()+
                            "/update-password?email="+supplier.getEmail()+"&uid="+supplier.getNid()+"&pass="+account.getPassword() +
                            "\nDon't share this link to anyone. Otherwise, anyone with this link can get access to your account!";

                    mail.send(sub,msg);
                    response.sendRedirect("DistributorPanel");


                }

        } catch (Exception e) {
            request.setAttribute("error",e);
            request.getRequestDispatcher("error/error.jsp").forward(request,response);
            throw new RuntimeException(e);
        }
    }
}
