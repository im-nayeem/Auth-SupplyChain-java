package com.example.my_web_app.common.model;

import javax.servlet.http.HttpServletRequest;

public class Distributor extends User{
    private String distCenterRoad;

    public Distributor(String distCenterRoad) {
        this.distCenterRoad = distCenterRoad;
    }

    public Distributor(int nid, String name, String email, String hash, String salt, Address address, String distCenterRoad) {
        super(nid, name, email, hash, salt, address);
        this.distCenterRoad = distCenterRoad;
    }

    public Distributor(final HttpServletRequest request) {
        super(request);
        this.distCenterRoad = distCenterRoad;
    }

    public String getDistCenterRoad() {
        return distCenterRoad;
    }
}
