package com.my_web_app.common.model;

import DB.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Company {
    private String name,address,phoneNumber,email,description,logoUrl;
    DatabaseConnection conn = null;

    /**================Constructor==================**/
    public Company()
    {
        try{
            conn = new DatabaseConnection();
            ResultSet rs = conn.executeQuery("SELECT * FROM company_info;");
            rs.next();
            name = rs.getString("name");
            address = rs.getString("address");
            phoneNumber = rs.getString("phone_number");
            email = rs.getString("email");
            description = rs.getString("description");
            logoUrl = rs.getString("logo_url");

        } catch (Exception e) {
            throw new RuntimeException(e+" \nCompany");
        }
        finally {
            conn.close();
        }
    }

    public Company(final HttpServletRequest request) {
       try{
           this.name = request.getParameter("company-name");
           this.address = request.getParameter("address");
           this.phoneNumber = request.getParameter("phone-number");
           this.email = request.getParameter("email");
           this.description = request.getParameter("description");
           this.logoUrl = request.getParameter("logo-url");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    /**================== Methods ===================**/

    public void storeInDatabase()
    {
        try{
            conn = new DatabaseConnection();

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO company_info(name,address,phone_number,email,description,logo_url) VALUES(?,?,?,?,?,?);");

            pstmt.setString(1,name);
            pstmt.setString(2,address);
            pstmt.setString(3,phoneNumber);
            pstmt.setString(4,email);
            pstmt.setString(5,description);
            pstmt.setString(6,logoUrl);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }

    /**--------Getters----------------------**/
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

}
