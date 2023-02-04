package com.example.my_web_app.admin.model;

import DB.DatabaseConnection;

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
    private String batchId,productCode,manufacDate,expDate;
    private int totalProduct=0,warrantyYear=0,warrantyMonth=0,produced=0;
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
            this.produced=resultSet.getInt("produced");

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
            warrantyYear = Integer.parseInt(request.getParameter("warranty-year"));
            warrantyMonth = Integer.parseInt(request.getParameter("warranty-month"));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e+"\n Couldn't Get Form Data!");
        }
    }
    public  ProductBatch(final ResultSet resultSet) throws Exception {

        this.batchId = resultSet.getString("batch_id");
        this.productCode = resultSet.getString("p_code");
        this.totalProduct = resultSet.getInt("total_product");
        this.warrantyMonth = resultSet.getInt("warranty_month");
        this.warrantyYear = resultSet.getInt("warranty_year");
        this.manufacDate = resultSet.getString("manufac_date");
        this.expDate = resultSet.getString("exp_date");
        this.produced=resultSet.getInt("produced");
    }

    /**==================== Methods ==========================**/
    public  void storeInDatabase()
    {
        try{
            conn = new DatabaseConnection();
            PreparedStatement preparedStatement = conn.getPreparedStatement("INSERT INTO batch(batch_id,total_product,p_code,manufac_date,exp_date,warranty_year,warranty_month) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,batchId);
            preparedStatement.setInt(2,totalProduct);
            preparedStatement.setString(3,productCode);
            preparedStatement.setString(4,manufacDate);
            preparedStatement.setString(5,expDate);
            preparedStatement.setInt(6,warrantyYear);
            preparedStatement.setInt(7,warrantyMonth);

            preparedStatement.execute();

        } catch (Exception e) {
            throw new RuntimeException(e+"\nCouldn't Store Batch Info In Database");
        }
        finally {
            conn.close();
        }
    }
    public List<ProductBatch> getAllBatch()
    {
        try {
            List<ProductBatch>batchList = new ArrayList<>();
            conn = new DatabaseConnection();
            ResultSet resultSet = conn.executeQuery("SELECT * FROM batch");
            while(resultSet.next())
                batchList.add(new ProductBatch(resultSet));
            return  batchList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
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

    public int getProduced() {
        return produced;
    }
}
