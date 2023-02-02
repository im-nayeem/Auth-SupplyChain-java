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
    private int warrantyYear=0,warrantyMonth=0;
    private  DatabaseConnection conn;

    /**----------Constructor------------**/
    public ProductBatch(final HttpServletRequest request) {
        try{
            productCode = request.getParameter("product-code");
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
            PreparedStatement preparedStatement = conn.getPreparedStatement("INSERT INTO batch(batch_id,p_code,manufac_date,exp_date,warranty_year,warranty_month) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,batchId);
            preparedStatement.setString(2,productCode);
            preparedStatement.setString(3,manufacDate);
            preparedStatement.setString(4,expDate);
            preparedStatement.setInt(5,warrantyYear);
            preparedStatement.setInt(6,warrantyMonth);

            preparedStatement.execute();

        } catch (Exception e) {
            throw new RuntimeException(e+"\nCouldn't Store Batch Info In Database");
        }
        finally {
            conn.close();
        }
    }
}
