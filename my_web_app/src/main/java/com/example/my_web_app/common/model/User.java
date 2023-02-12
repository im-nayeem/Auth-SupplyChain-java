package com.example.my_web_app.common.model;

import javax.servlet.http.HttpServletRequest;

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

    public User() {
    }


    public User(int nid, String name, String email, String hash, String salt, Address address) {
        this.nid = nid;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.address = address;
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public User(HttpServletRequest request) {
        this.nid = Integer.parseInt(request.getParameter("nid"));
        this.name = request.getParameter("name");
        this.email = request.getParameter("email");
        this.hash = "";
        this.salt = "";

        String addId = request.getParameter("division")+""+request.getParameter("district")+request.getParameter("upazila")+request.getParameter("union");
        String division = Address.getDivisionList().get(Integer.parseInt(request.getParameter("division"))).getName();
        String district = Address.getDistrictList().get(Integer.parseInt(request.getParameter("district"))).getName();
        String upazila = Address.getUpazilaList().get(Integer.parseInt(request.getParameter("upazila"))).getName();
        String union = Address.getUnionList().get(Integer.parseInt(request.getParameter("union"))).getName();

        Address address = new Address(addId,division,district,upazila,union);
        this.address = address;
    }


    /**==============Methods====================*/
    protected void storeInDatabase()
    {
        try {
            address.storeInDatabase();
            //store user
        } catch (Exception e) {
            throw new RuntimeException(e);
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


