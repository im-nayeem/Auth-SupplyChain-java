package com.my_web_app.distributor;


import DB.DatabaseConnection;
import com.my_web_app.common.model.Address;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

public class Distributor extends User {
    private String distCenterRoad;
    DatabaseConnection conn = null;

    public Distributor() {
    }


    public Distributor(String distCenterRoad) {
        this.distCenterRoad = distCenterRoad;
    }


    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Distributor(final HttpServletRequest request, String role)  {
        super(request,role);
        try{
            this.distCenterRoad = request.getParameter("dcenter-road");
        } catch (Exception e) {
            throw new RuntimeException(e+" Distributor Constructor");
        }
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            super.storeInDatabase();

            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO distributor(uid,dist_center_road) VALUES(?,?)");
            pstmt.setLong(1,getNid());
            pstmt.setString(2, distCenterRoad);

            pstmt.execute();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }
    public String getDistCenterRoad() {
        return distCenterRoad;
    }

}