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
    long lastHolder=0,distributor=0,distributorAgent=0,seller=0;
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
        this.status = "manufactured";

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
            this.distributor = resultSet.getLong("distributor");
            this.distributorAgent = resultSet.getLong("distributor_agent");
            this.seller = resultSet.getLong("seller");

        } catch (Exception e) {
            throw new RuntimeException(e+" in Product.java");
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
            this.distributor = resultSet.getLong("distributor");
            this.distributorAgent = resultSet.getLong("distributor_agent");
            this.seller = resultSet.getLong("seller");

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
     * Used for maintaining transaction properties
     * @param tableName the table name to store product info
     * @param conn the DatabaseConnection object of the caller method
     */
 public void storeInDatabase(String tableName,DatabaseConnection conn)
    {
        try{
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO "+tableName+"(pid,status,sold_date,batch) VALUES(?,?,?,?)");
            pstmt.setString(1,productId);
            pstmt.setString(2,status);
            pstmt.setString(3,soldDate);
            pstmt.setString(4,batchId);
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e+" Product Class");
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
             String query = "UPDATE "+tableName+" SET last_holder=? WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct)+" AND last_holder=?";
             PreparedStatement pstmt = conn.getPreparedStatement( query);
             pstmt.setLong(1,newHolder);
             pstmt.setLong(2,currentHolder);

             pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e + "Update Holder");
        }
        finally {
            if(conn!=null)
                conn.close();
        }

    }
    /**
     * updateProductHolder method to update the holder of the product(last holder)
     * @param tableName the table's name in database containing the products
     * @param firstProduct the first product's id
     * @param lastProduct the last product's id
     * @param newHolder new holder's(of the products) nid
     */
    public static  void updateProductHolder(DatabaseConnection conn, String tableName,String firstProduct,String lastProduct,long newHolder){
        try{
            String query = "UPDATE "+tableName+" SET last_holder=? WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setLong(1,newHolder);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * updateProductStatus method to update the status of the product
     * @param status the new status of the product
     */
    public static void updateProductStatus(DatabaseConnection conn, String tableName,String firstProduct,String lastProduct,String status){
        try{
            String query = "UPDATE "+tableName+" SET status=? WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setString(1,status);

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * updateHandOverDate method to update the handover date(today) of the product
     */
    public  static void updateHandOverDate(DatabaseConnection conn, String tableName, String firstProduct, String lastProduct){
        try{
            String query = "UPDATE "+tableName+" SET sold_date=CURDATE() WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            conn.executeUpdate(query);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method to check if the product status matches with a given status
     * @param status the given status to check if it matches with the product status
     * @return True if matches else false
     */
    public static Boolean hasValidStatus(String tableName,String firstProduct,String lastProduct,String status){
        Boolean ok = false;
        DatabaseConnection conn = null;
        try {
            conn = new DatabaseConnection();
            String query = "SELECT COUNT(*) AS x FROM "+tableName+" WHERE status=? AND pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);
            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setString(1,status);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int x = rs.getInt("x");

            rs = conn.executeQuery("SELECT COUNT(*) AS y FROM "+tableName+" WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct));

            rs.next();
            int y = rs.getInt("y");

            if(y != 0 && y - x == 0)
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
    public long getDistributor(){return distributor;}

    public long getDistributorAgent() {
        return distributorAgent;
    }

    public long getSeller() {
        return seller;
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
