package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.Utility;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
public class ProductBatch{
    private String batchId,productCode;
    private String manufacDate = null,expDate = null;
    private int totalProduct=0,warrantyYear=0,warrantyMonth=0;
    private  DatabaseConnection conn;

    /**----------Constructor------------**/
    public ProductBatch(){

    }
    public ProductBatch(String batchId)
    {
        try{
            conn = new DatabaseConnection();
            PreparedStatement pstmt= conn.getPreparedStatement("SELECT * FROM batch WHERE batch_id=?");
            pstmt.setString(1,batchId);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();

            this.batchId = resultSet.getString("batch_id");
            this.productCode = resultSet.getString("p_code");
            this.totalProduct = resultSet.getInt("total_product");
            this.warrantyMonth = resultSet.getInt("warranty_month");
            this.warrantyYear = resultSet.getInt("warranty_year");
            this.manufacDate = resultSet.getString("manufac_date");
            this.expDate = resultSet.getString("exp_date");

        } catch (Exception e) {
            throw new RuntimeException(e+" Product Batch");
        }
        finally {
            conn.close();
        }

    }

    public ProductBatch(final HttpServletRequest request) {
        try{
            totalProduct = Integer.parseInt(request.getParameter("total-product"));
            batchId = request.getParameter("batch-id");
            productCode = request.getParameter("product-code");
            manufacDate = request.getParameter("manufacturing-date");
            expDate = request.getParameter("expire-date");
            if(request.getParameter("warranty-year") != null) {
                warrantyYear = Integer.parseInt(request.getParameter("warranty-year"));
            }
            if(request.getParameter("warranty-month") != null) {
                warrantyMonth = Integer.parseInt(request.getParameter("warranty-month"));
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e+"\n Couldn't Get Form Data!");
        }
    }
    public  ProductBatch(final ResultSet resultSet) {

        try{
            this.batchId = resultSet.getString("batch_id");
            this.productCode = resultSet.getString("p_code");
            this.totalProduct = resultSet.getInt("total_product");
            this.warrantyMonth = resultSet.getInt("warranty_month");
            this.warrantyYear = resultSet.getInt("warranty_year");
            this.manufacDate = resultSet.getString("manufac_date");
            this.expDate = resultSet.getString("exp_date");
        } catch (Exception e) {
            throw new RuntimeException(e+" ProductBatch");
        }
    }

    /**==================== Methods ==========================**/
    public void generateProductInfo() {
        try {
            //At first get the number of products containing this product code, it will be the starting product number for this batch. Format of pid:[product code + product number]

            ProductMap productMap = new ProductMap(this.productCode);
            ResultSet resultSet = conn.executeQuery("SELECT COUNT(*) AS n FROM "+productMap.getTableName());
            resultSet.next();
            int start = resultSet.getInt("n");

            //Execute batch query to insert info for all products
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO "+productMap.getTableName()+"(pid,status,sold_date,batch) VALUES(?,?,?,?)");
            for(int i = start; i < this.totalProduct + start; i++)
            {
                pstmt.setString(1,this.productCode + i);
                pstmt.setString(2, Utility.productStatusByRole("admin"));
                pstmt.setString(3,null);
                pstmt.setString(4,batchId);

                // add batch to prepared statement
                pstmt.addBatch();

            }
            //execute batch query
            int[] queryRes = pstmt.executeBatch();


        } catch (Exception e) {
            throw new RuntimeException(e+" \nError with generating Product Info");
        }

    }

    public  void storeInDatabase()
    {
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.getPreparedStatement("INSERT INTO batch(batch_id,total_product,p_code,manufac_date,exp_date,warranty_year,warranty_month) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,batchId);
            preparedStatement.setInt(2,totalProduct);
            preparedStatement.setString(3,productCode);
            preparedStatement.setString(4,manufacDate);
            preparedStatement.setString(5,expDate);
            preparedStatement.setInt(6,warrantyYear);
            preparedStatement.setInt(7,warrantyMonth);

            preparedStatement.execute();
            generateProductInfo();

            conn.commit();


        } catch (Exception e) {
            if(conn!=null)
                conn.rollback();
            throw new RuntimeException(e+"\nCouldn't Store Batch Info In Database");
        }
        finally {
            conn.close();
        }
    }

    /**
     *
     * Method to get list of all batches
     * @return batchList the list of all batches
     */
    public List<ProductBatch> getAllBatch(String productCode)
    {
        List<ProductBatch>batchList = new ArrayList<>();

        try {
            conn = new DatabaseConnection();
            PreparedStatement preparedStatement = conn.getPreparedStatement("SELECT * FROM batch WHERE p_code=?");
            preparedStatement.setString(1,productCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                batchList.add(new ProductBatch(resultSet));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
        return  batchList;
    }

    /**
     * Method to get all products for this batch
     * @return lst the list of all product
     */
    public List<Product> getAllProduct()
    {
        try{
            conn = new DatabaseConnection();

            List<Product> lst = new ArrayList<>();
            ProductMap productMap = new ProductMap(productCode);

            PreparedStatement pstmt = conn.getPreparedStatement("SELECT * FROM "+productMap.getTableName()+" WHERE batch = ?");
            pstmt.setString(1,batchId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
                lst.add(new Product(rs));

            return lst;

        } catch (Exception e) {
            throw new RuntimeException(e+" getAllProduct");
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }

    public String getBatchId() {
        return batchId;
    }

    public String getManufacDate() {
        return manufacDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public int getWarrantyYear() {
        return warrantyYear;
    }

    public int getWarrantyMonth() {
        return warrantyMonth;
    }

    public String getProductCode() {
        return productCode;
    }

}
