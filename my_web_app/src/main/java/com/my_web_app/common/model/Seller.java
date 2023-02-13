package com.my_web_app.common.model;

import DB.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

public class Seller extends User{
    private String shopName,shopRoad;
    DatabaseConnection conn = null;

    public Seller() {
    }

    public Seller(int nid, String name, String email, String hash, String salt, Address address,String role) {
        super(nid, name, email, hash, salt, address, role);
    }

    public Seller(String shopName, String shopRoad) {
        this.shopName = shopName;
        this.shopRoad = shopRoad;
    }

    public Seller(int nid, String name, String email, String hash, String salt, Address address, String shopName, String shopRoad,String role) {
        super(nid, name, email, hash, salt, address, role);
        this.shopName = shopName;
        this.shopRoad = shopRoad;
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Seller(final HttpServletRequest request)  {
        super(request);
        try{
            this.shopName = request.getParameter("shop-name");
            this.shopRoad = request.getParameter("shop-road");
        } catch (Exception e) {
            throw new RuntimeException(e+" Seller Constructor");
        }
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            super.storeInDatabase();

            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO seller(uid,shop_name,shop_road) VALUES(?,?,?)");
            pstmt.setInt(1,getNid());
            pstmt.setString(2,shopName);
            pstmt.setString(3,shopRoad);

            pstmt.execute();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }
    public String getShopName() {
        return shopName;
    }

    public String getShopRoad() {
        return shopRoad;
    }
}
