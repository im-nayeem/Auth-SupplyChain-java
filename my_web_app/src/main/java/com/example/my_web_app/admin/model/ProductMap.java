package com.example.my_web_app.admin.model;

import DB.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
public class ProductMap {
    private String productName="";
    private String productCode="";
    private String haveWarranty="";
    private  String haveExpiration="";
    private String tableName="";
    private int produced=0;

    /**----Constructor-----**/
    public ProductMap(){

    }
    public ProductMap(final HttpServletRequest request) {
        try{
            productName = request.getParameter("product-name");
            productCode = request.getParameter("product-code");
            haveWarranty=request.getParameter("having-warranty");
            haveExpiration=request.getParameter("having-expired-date");
            tableName = request.getParameter("table-name");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e+"\nCouldn't Read Form Data!");
        }
    }

    /**=========Methods===========**/
    public void storeInDatabase()
    {
        DatabaseConnection conn = null;
        try {
            conn = new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO product_map(name,p_code,have_warranty,have_expiration,table_name) VALUES(?,?,?,?,?)");
            pstmt.setString(1,productName);
            pstmt.setString(2,productCode);
            pstmt.setString(3,haveWarranty);
            pstmt.setString(4,haveExpiration);
            pstmt.setString(5,tableName);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e+"\nCouldn't Store in Database!");
        }
        finally {
            conn.close();
        }
    }

    public void markAsProduced()
    {

    }

    public void getProductInfo(String productCode)
    {

    }

    public String getProductCode() {
        return productCode;
    }
}
