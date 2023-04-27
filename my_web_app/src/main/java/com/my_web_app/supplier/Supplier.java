package com.my_web_app.supplier;


import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Supplier extends User {
    DatabaseConnection conn = null;

    public Supplier() {
    }
    public Supplier(long uid) throws  Exception{
        super(uid);

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
            conn = new DatabaseConnection();
            // start the transaction
            conn.setAutoCommit(false);

            super.storeInDatabase(conn);


            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO supplier(uid) VALUES(?)");
            pstmt.setLong(1,getNid());

            pstmt.execute();

            // If everything has gone well so far, commit the transaction
            conn.commit();


        } catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }

    public void updateProductSeller(String tableName,String firstProduct,String lastProduct,long seller){
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);
            String query = "UPDATE "+tableName+" SET seller=? WHERE pid IN "+ Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1,seller);
            preparedStatement.executeUpdate();
            conn.commit();

        }catch (Exception e) {
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

}