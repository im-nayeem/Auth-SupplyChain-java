package com.my_web_app.admin.model;

import DB.DatabaseConnection;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductBatch;
import com.my_web_app.common.model.ProductMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created on 03-Feb-23
 *
 * @author Nayeem
 */
public class ProductInfoGenerator {
    private  DatabaseConnection conn = null;
    public ProductInfoGenerator() {
    }
    public void generateProductInfo(String batchId) {


        try {
             conn = new DatabaseConnection();

             conn.setAutoCommit(false);
             ProductBatch productBatch  = new ProductBatch(batchId);
             ProductMap productMap = new ProductMap(productBatch.getProductCode());
             if(productBatch.getProduced()==1)
             {
                 throw  new RuntimeException("Product is Already Produced for This Batch!");
             }

             ResultSet resultSet=conn.executeQuery("SELECT COUNT(*) AS n FROM "+productMap.getTableName());
             resultSet.next();
             int start=resultSet.getInt("n");

             for(int i=start;i<productBatch.getTotalProduct()+start;i++)
             {
                 Product product = new Product(productBatch.getProductCode()+i, batchId);

                 product.storeInDatabase(productMap.getTableName());
             }

            PreparedStatement preparedStatement = conn.getPreparedStatement("UPDATE batch SET produced=? where batch_id=?");
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,batchId);
            preparedStatement.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if(conn!=null)
                conn.rollback();
            throw new RuntimeException(e+" \nProductInfoGenerator");
        }
        finally {
            conn.close();
        }

    }
}
