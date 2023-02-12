package com.example.my_web_app.common.model;

import javax.servlet.http.HttpServletRequest;

public class Seller extends User{
    private String shopName,shopRoad;

    public Seller() {
    }

    public Seller(int nid, String name, String email, String hash, String salt, Address address) {
        super(nid, name, email, hash, salt, address);
    }

    public Seller(String shopName, String shopRoad) {
        this.shopName = shopName;
        this.shopRoad = shopRoad;
    }

    public Seller(int nid, String name, String email, String hash, String salt, Address address, String shopName, String shopRoad) {
        super(nid, name, email, hash, salt, address);
        this.shopName = shopName;
        this.shopRoad = shopRoad;
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Seller(final HttpServletRequest request) {
        super(request);
        this.shopName = request.getParameter("shop-name");
        this.shopRoad = request.getParameter("shop-road");
    }
    /**=================Methods====================*/
    public void StoreInDatabase(){
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getShopName() {
        return shopName;
    }

    public String getShopRoad() {
        return shopRoad;
    }
}
