package com.my_web_app.distributor;


import DB.DatabaseConnection;
import com.my_web_app.common.model.Address;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Distributor extends User {
    private String distCenterRoad;
    DatabaseConnection conn = null;

    public Distributor() {
    }


    public Distributor(String distCenterRoad) {
        this.distCenterRoad = distCenterRoad;
    }

    /**
     * Constructor to retrieve data
     * @param uid the user's nid
     * @throws Exception
     */
    public Distributor(long uid) throws  Exception{
        super(uid);
        conn = new DatabaseConnection();
        ResultSet resultSet = conn.executeQuery("SELECT * FROM seller WHERE uid="+uid);
        if(resultSet.next()){
            this.distCenterRoad = resultSet.getString("dist_center_road");
        }
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
            conn = new DatabaseConnection();
            // start the transaction
            conn.setAutoCommit(false);

            super.storeInDatabase(conn);

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO distributor(uid,dist_center_road) VALUES(?,?)");
            pstmt.setLong(1,getNid());
            pstmt.setString(2, distCenterRoad);

            pstmt.execute();

            // If everything has gone well so far, commit the transaction
            conn.commit();


        } catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    public String getDistCenterRoad() {
        return distCenterRoad;
    }

}