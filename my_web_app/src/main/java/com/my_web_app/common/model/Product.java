package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
public class Product{
    private String productId,batchId;
    private  String status=null,soldDate=null;
    long lastHolder=0;
    private static DatabaseConnection conn = null;

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
            this.lastHolder = resultSet.getLong("last_holder");

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
            this.lastHolder = resultSet.getLong("last_holder");
        } catch (Exception e) {
            throw new RuntimeException(e+" Product");
        }
    }

    /** ============ Methods ==============**/
    public void storeInDatabase(String tableName)
    {
        try{
            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO "+tableName+"(pid,status,sold_date,batch) VALUES(?,?,?,?)");


            pstmt.setString(1,productId);
            pstmt.setString(2,status);
            pstmt.setString(3,soldDate);
            pstmt.setString(4,batchId);


            pstmt.execute();


        } catch (Exception e) {
            throw new RuntimeException(e+" Product Class");
        }
        finally {
            if(conn!=null)
            conn.close();
        }
    }

    public static void updateProductHolder(String tableName,String firstProduct,String lastProduct,long currentHolder,long newHolder){
        try{
             conn = new DatabaseConnection();
             String query = "UPDATE "+tableName+" SET last_holder=? WHERE pid BETWEEN ? and ? and last_holder=?";
             PreparedStatement pstmt = conn.getPreparedStatement( query);
             pstmt.setLong(1,newHolder);
             pstmt.setString(2,firstProduct);
             pstmt.setString(3,lastProduct);
             pstmt.setLong(4,currentHolder);

             pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e + "Update Holder");
        }
        finally {
            if(conn!=null)
                conn.close();
        }

    }
    public static  void updateProductHolder(String tableName,String firstProduct,String lastProduct,long newHolder){
        try{
            conn = new DatabaseConnection();
            String query = "UPDATE "+tableName+" SET last_holder=? WHERE pid BETWEEN ? and ?";

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setLong(1,newHolder);
            pstmt.setString(2,firstProduct);
            pstmt.setString(3,lastProduct);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }

    }

    public static void updateProductStatus(String tableName,String firstProduct,String lastProduct,String status){
        try{
            conn = new DatabaseConnection();
            String query = "UPDATE "+tableName+" SET status=? WHERE pid BETWEEN ? and ?";

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setString(1,status);
            pstmt.setString(2,firstProduct);
            pstmt.setString(3,lastProduct);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    public  static void updateSoldDate(String tableName,String firstProduct,String lastProduct){
        try{
            conn = new DatabaseConnection();
            String query = "UPDATE "+tableName+" SET sold_date=CURDATE() WHERE pid BETWEEN ? and ?";

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setString(1,firstProduct);
            pstmt.setString(2,lastProduct);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
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

    public long getLastHolder() {
        return lastHolder;
    }

    public String getSoldDate() {
        return soldDate;
    }
}
