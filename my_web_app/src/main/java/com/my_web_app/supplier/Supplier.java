package com.my_web_app.supplier;


import DB.DatabaseConnection;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

public class Supplier extends User {
    DatabaseConnection conn = null;

    public Supplier() {
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Supplier(final HttpServletRequest request, String role)  {
        super(request,role);
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            super.storeInDatabase();

            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO supplier(uid) VALUES(?)");
            pstmt.setLong(1,getNid());

            pstmt.execute();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }

}