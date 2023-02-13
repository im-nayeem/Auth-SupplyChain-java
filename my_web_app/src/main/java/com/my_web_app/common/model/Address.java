package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.Location;
import com.my_web_app.Utility;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
        try{
            this.addrId = addrId;
            this.division = division;
            this.district = district;
            this.upazila = upazila;
            this.union = uniion;
        } catch (Exception e) {
            throw new RuntimeException(e + " Address Constructor");
        }
    }

    public void storeInDatabase()
    {
        try{

            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO address(address_id,division,district,upazila,uniion) VALUES(?,?,?,?,?)");
            pstmt.setString(1,addrId);
            pstmt.setString(2,division);
            pstmt.setString(3,district);
            pstmt.setString(4,upazila);
            pstmt.setString(5,union);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e + " Address Store");
        }
        finally {
            conn.close();
        }
    }

    public String getAddrId() {
        return addrId;
    }

    public String getDivision() {
        return division;
    }

    public String getDistrict() {
        return district;
    }

    public String getUpazila() {
        return upazila;
    }

    public String getUnion() {
        return union;
    }

    public static List<Location> getDivisionList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/divisions/divisions.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Location> getDistrictList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/districts/districts.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Location> getUpazilaList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/upazilas/upazilas.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Location> getUnionList()
    {
        try {
            return Utility.getGeoList(new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/unions/unions.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
