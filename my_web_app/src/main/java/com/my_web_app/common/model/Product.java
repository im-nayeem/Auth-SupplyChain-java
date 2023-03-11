package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    /**
     * Constructor to assign info
     * @param productId the product's unique ID
     * @param batchId the product's batch id
     */
    public Product(String productId, String batchId) {
        this.productId = productId;
        this.batchId = batchId;
        this.status = "produced";

    }

    /**
     * Constructor to retrieve product info
     * @param productId the product's id to retrieve info
     */
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
            throw new RuntimeException(e+" Product");
        }
        finally {
            conn.close();
        }
    }

    /**
     * Constructor to store info
     * @param resultSet the ResultSet object containing info retrieved from DB
     */
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
    /**---------------------------------------------------------------------*/


    /** ============================= Methods ============================**/

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

    /**
     * updateProductHolder method to update the holder of the product
     * @param tableName the table's name in database containing the products
     * @param firstProduct the first product's id
     * @param lastProduct the last product's id
     * @param currentHolder current holder's(of the products) nid
     * @param newHolder new holder's(of the products) nid
     */
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
    /**
     * updateProductHolder method to update the holder of the product
     * @param tableName the table's name in database containing the products
     * @param firstProduct the first product's id
     * @param lastProduct the last product's id
     * @param newHolder new holder's(of the products) nid
     */
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

    /**
     * updateProductStatus method to update the status of the product
     * @param status the new status of the product
     */
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

    /**
     * updateSoldDate method to update the handover date(today) of the product
     */
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

    /**
     * hasValidStatus method to check if the product status matches with a given status
     * @param status the given status to check if it matches with the product status
     * @return True if matches else false
     */
    public static Boolean hasValidStatus(String tableName,String firstProduct,String lastProduct,String status){
        Boolean ok = false;
        DatabaseConnection conn = null;
        try {
            conn = new DatabaseConnection();
            String query = "SELECT COUNT(*) AS x FROM "+tableName+" WHERE status=? AND pid BETWEEN ? AND ?;";
            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setString(1,status);
            pstmt.setString(2,firstProduct);
            pstmt.setString(3,lastProduct);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int x = rs.getInt("x");

            pstmt = conn.getPreparedStatement("SELECT COUNT(*) AS y FROM "+tableName+" WHERE pid BETWEEN ? AND ?;");
            pstmt.setString(1,firstProduct);
            pstmt.setString(2,lastProduct);

            rs = pstmt.executeQuery();
            rs.next();
            int y = rs.getInt("y");

            if(x!=0 && y!=0 && y-x==0)
                ok = true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }
        return ok;
    }

    /**---------------------------------------------------------------------*/


    /**--------------------------Getters----------------------------------*/

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

    /**
     * Method to calculate the remaining warranty of the product
     * @return remaining warranty in days
     */
    public long getRemWarranty() {
        String soldDateString = this.soldDate;

        LocalDate soldDate = LocalDate.parse(soldDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate today = LocalDate.now();

        long daysSinceSold = ChronoUnit.DAYS.between(soldDate, today);
        ProductBatch productBatch = new ProductBatch(this.getBatchId());

        return productBatch.getWarrantyYear()*365+productBatch.getWarrantyMonth()*30-daysSinceSold;

    }
}
