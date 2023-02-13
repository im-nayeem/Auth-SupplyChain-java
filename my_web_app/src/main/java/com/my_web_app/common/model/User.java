package com.my_web_app.common.model;

import DB.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;

/**
 * Created on 06-Feb-23
 *
 * @author Nayeem
 */
public class User {
    private int nid;
    private String name, email;
    private String hash,salt;
    private Address address;
    private String role;
    DatabaseConnection conn = null;

    public User() {
    }


    public User(int nid, String name, String email, String hash, String salt, Address address,String role) {
        this.nid = nid;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.address = address;
        this.role = toLowerCase(role);
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public User(HttpServletRequest request) {
       try{
           this.nid = Integer.parseInt(request.getParameter("nid"));
           this.name = request.getParameter("name");
           this.email = request.getParameter("email");
           this.hash = "";
           this.salt = "";
           this.role=toLowerCase(role);

           String addId = request.getParameter("division")+""+request.getParameter("district")+request.getParameter("upazila")+request.getParameter("union");
           String division = Address.getDivisionList().get(Integer.parseInt(request.getParameter("division"))-1).getName();
           String district = Address.getDistrictList().get(Integer.parseInt(request.getParameter("district"))-1).getName();
           String upazila = Address.getUpazilaList().get(Integer.parseInt(request.getParameter("upazila"))-1).getName();
           String union = Address.getUnionList().get(Integer.parseInt(request.getParameter("union"))-1).getName();

           this.address = new Address(addId,division,district,upazila,union);
       } catch (NumberFormatException e) {
           throw new RuntimeException(e+" User Constructor");
       }
    }


    /**==============Methods====================*/
    protected void storeInDatabase()
    {
        try {
            address.storeInDatabase();
            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO users(name,nid,email,address_id) VALUES(?,?,?,?)");
            pstmt.setString(1,this.name);
            pstmt.setInt(2,nid);
            pstmt.setString(3,email);
            pstmt.setString(4,address.getAddrId());

            pstmt.execute();


        } catch (Exception e) {
            throw new RuntimeException(e + "User");
        }
        finally {
            conn.close();
        }
    }

    /**------------Getter------------**/
    protected int getNid() {
        return nid;
    }

    protected String getName() {
        return name;
    }

    protected String getEmail() {
        return email;
    }

    protected Address getAddress() {
        return address;
    }

}


