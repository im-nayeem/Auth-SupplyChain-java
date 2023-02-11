package com.example.my_web_app.common.model;

import DB.DatabaseConnection;
import com.example.my_web_app.Utility;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class Address {
    private String addrId;
    private String division=null,district=null,upazila=null,union=null;
    DatabaseConnection conn = null;
    public Address(){

    }
    public Address(String id){
        try{
            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("SELECT * FROM address WHERE address_id=?");
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            this.addrId = id;
            this.division = rs.getString("division");
            this.district = rs.getString("district");
            this.upazila = rs.getString("upazila");
            this.union = rs.getString("uniion");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }

    }

    public Address(String addrId, String division, String district, String upazila, String uniion) {
        this.addrId = addrId;
        this.division = division;
        this.district = district;
        this.upazila = upazila;
        this.union = uniion;
    }

    public void storeInDatabase()
    {
        try{

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }

    public static Map<Integer,String> getDivisionList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/divisions/divisions.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<Integer,String> getDistrictList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/districts/districts.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<Integer,String> getUpazilaList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/upazilas/upazilas.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static Map<Integer,String > getUnionList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/unions/unions.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
