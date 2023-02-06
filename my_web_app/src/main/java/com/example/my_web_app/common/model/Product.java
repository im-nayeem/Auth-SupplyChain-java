package com.example.my_web_app.common.model;

import DB.DatabaseConnection;
import com.example.my_web_app.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
public class Product{
    private String productId,batchId;
    private  String status=null,lastHolder=null,soldDate=null;
    DatabaseConnection conn = null;

    public Product() {

    }
    public Product(String productId, String batchId) {
        this.productId = productId;
        this.batchId = batchId;
        this.status = "produced";
    }
    public Product(String productId)
    {
        try{
            conn  = new DatabaseConnection();
            ProductMap productMap = new ProductMap(Utility.getCode(productId));

            PreparedStatement pstmt  = conn.getPreparedStatement("SELECT * FROM "+productMap.getTableName()+" WHERE pid=?");
            pstmt.setString(1,productId);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            this.productId = resultSet.getString("pid");
            this.batchId  = resultSet.getString("batch");
            this.status = resultSet.getString("status");
            this.soldDate = resultSet.getString("sold_date");
            this.lastHolder = resultSet.getString("last_holder");

        } catch (Exception e) {
            throw new RuntimeException(e+"\nProduct");
        }
        finally {
            conn.close();
        }
    }

    public Product(final ResultSet resultSet)
    {
        try
        {
            this.productId = resultSet.getString("pid");
            this.batchId  = resultSet.getString("batch");
            this.status = resultSet.getString("status");
            this.soldDate = resultSet.getString("sold_date");
            this.lastHolder = resultSet.getString("last_holder");
        } catch (Exception e) {
            throw new RuntimeException(e+" Product");
        }
    }

    /** ============ Methods ==============**/
    public void storeInDatabase(String tableName)
    {
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

    public String getProductId() {
        return productId;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getStatus() {
        return status;
    }

    public String getLastHolder() {
        return lastHolder;
    }

    public String getSoldDate() {
        return soldDate;
    }
}
