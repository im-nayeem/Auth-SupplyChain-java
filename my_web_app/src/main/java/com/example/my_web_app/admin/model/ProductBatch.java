package com.example.my_web_app.admin.model;

import DB.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
public class ProductBatch {
    private String productName,productCode,batchId,manufacDate,expDate;
    private int totalProduct=0,warrantyYear=0,warrantyMonth=0;
    private  DatabaseConnection conn;

    /**----------Constructor------------**/
    public ProductBatch(final HttpServletRequest request) {
        try{
            productCode = request.getParameter("product-code");
            totalProduct = Integer.parseInt(request.getParameter("total-product"));
            batchId = request.getParameter("batch-id");
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
}
