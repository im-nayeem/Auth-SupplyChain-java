package com.my_web_app.distributor;

import com.my_web_app.common.model.Address;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;

public class Distributor extends User {
    private String distCenterRoad;

    public Distributor(String distCenterRoad) {
        this.distCenterRoad = distCenterRoad;
    }

    public Distributor(int nid, String name, String email, String hash, String salt, Address address, String distCenterRoad) {
        super(nid, name, email, hash, salt, address,"");
        this.distCenterRoad = distCenterRoad;
    }

    public Distributor(final HttpServletRequest request,String role) {
        super(request,"");
        this.distCenterRoad = distCenterRoad;
    }

    public String getDistCenterRoad() {
        return distCenterRoad;
    }
}
