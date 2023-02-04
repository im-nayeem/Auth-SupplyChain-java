package com.example.my_web_app.admin.model;

import DB.DatabaseConnection;

import java.sql.PreparedStatement;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
public class Product{
    private String productId,batchId;
    private  String status=null,lastHolder=null,soldDate=null;
    public Product() {

    }
    public Product(String productId, String batchId) {
        this.productId = productId;
        this.batchId = batchId;
        this.status = "produced";
    }

    public void storeInDatabase(String tableName)
    {
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO "+tableName+"(pid,status,sold_date,last_holder,batch) VALUES(?,?,?,?,?)");
            pstmt.setString(1,productId);
            pstmt.setString(2,status);
            pstmt.setString(3,soldDate);
            pstmt.setString(4,lastHolder);
            pstmt.setString(5,batchId);
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e+" Product Class");
        }
        finally {
            conn.close();
        }
    }

}
