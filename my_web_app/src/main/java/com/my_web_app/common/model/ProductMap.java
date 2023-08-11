package com.my_web_app.common.model;

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
 * Model class for product mapping and to create table for newly released product
 *
 * @author Nayeem
 */
public class ProductMap {
    private String productName="";
    private String productCode="";
    private String haveWarranty="";
    private  String haveExpiration="";
    private String tableName="";
    private static DatabaseConnection conn=null;

    /**----Constructor-----**/
    public ProductMap(){

    }

    /**
     * Constructor that retrieve info from DB
     * @param productCode the product code to retrieve info
     */
    public ProductMap(String productCode)
    {
        try{
            conn=new DatabaseConnection();
            PreparedStatement pstmt = conn.getPreparedStatement("SELECT * FROM product_map WHERE p_code=?");
            pstmt.setString(1,productCode);
            ResultSet rs= pstmt.executeQuery();
            rs.next();

            this.productName=rs.getString("name");
            this.productCode=rs.getString("p_code");
            this.haveWarranty=rs.getString("have_warranty");
            this.haveExpiration=rs.getString("have_expiration");
            this.tableName=rs.getString("table_name");


        } catch (Exception e) {
            throw new RuntimeException(e+"Product Map");
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    public ProductMap(final ResultSet rs){
        try{
            this.productName=rs.getString("name");
            this.productCode=rs.getString("p_code");
            this.haveWarranty=rs.getString("have_warranty");
            this.haveExpiration=rs.getString("have_expiration");
            this.tableName=rs.getString("table_name");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor
     * @param request the HttpServlet request to extract form data
     */
    public ProductMap(final HttpServletRequest request) {
        try{
            productName = request.getParameter("product-name");
            productCode = request.getParameter("product-code");
            haveWarranty=request.getParameter("having-warranty");
            haveExpiration=request.getParameter("having-expired-date");
            tableName = "table_"+productCode.toLowerCase();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e+"\nCouldn't Read Form Data!");
        }
    }

    /**====================Methods========================**/
    /**
     * Method to store newly released product info to PRODUCT-MAP
     */
    public void storeInDatabase()
    {
        try {
            conn = new DatabaseConnection();
            // start the transaction
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO product_map(name, p_code, have_warranty, have_expiration, table_name)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n" +
                    "ON DUPLICATE KEY UPDATE\n" +
                    "    name = VALUES(name),\n" +
                    "    have_warranty = VALUES(have_warranty),\n" +
                    "    have_expiration = VALUES(have_expiration),\n" +
                    "    table_name = VALUES(table_name);\n");

            pstmt.setString(1,productName);
            pstmt.setString(2,productCode);
            pstmt.setString(3,haveWarranty);
            pstmt.setString(4,haveExpiration);
            pstmt.setString(5,tableName);
            pstmt.executeUpdate();

            // create new table for this released product
            createProductTable(conn);

            // If everything has gone well so far, commit the transaction
            conn.commit();

        } catch (SQLException e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            throw new RuntimeException(e+"\nCouldn't Store in Database!");
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }

    /**
     * Method to create table for newly released product
     */
    private void createProductTable(DatabaseConnection conn)
    {
        try{
            String query = "CREATE TABLE "+tableName+"(\n"+
                    "    pid varchar(20) PRIMARY KEY,\n" +
                    "    status\tvarchar(15),\n" +
                    "    sold_date date null,\n" +
                    "    last_holder int,\n" +
                    "    batch varchar(15),\n" +
                    "    distributor int,\n"+
                    "    distributor_agent int,\n"+
                    "    seller int,\n"+
                    "    FOREIGN KEY(last_holder) REFERENCES users(nid),\n" +
                    "    FOREIGN KEY(distributor) REFERENCES users(nid),\n" +
                    "    FOREIGN KEY(distributor_agent) REFERENCES users(nid),\n" +
                    "    FOREIGN KEY(seller) REFERENCES users(nid),\n" +
                    "    FOREIGN KEY(batch) REFERENCES batch(batch_id) on UPDATE CASCADE on DELETE CASCADE\n" +
                    "    );";
            conn.execute(query);

        } catch (Exception e) {
            throw new RuntimeException(e+"\nCouldn't create table for "+productName+"!");
        }

    }

    /**
     * Method to affiliate product with distributor, agent or seller for history tracking purposes
     * @param nid the user's nid
     */
    public void setProductAffiliation(Long nid){
        conn = new DatabaseConnection();
        try{
            String query = "INSERT INTO user_product_affiliation (nid, p_code, affiliated)\n" +
                    "VALUES (?, ?, 1)\n" +
                    "ON DUPLICATE KEY UPDATE affiliated = 1;\n";
            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1, nid);
            preparedStatement.setString(2, this.getProductCode());
            preparedStatement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }



    /**-------------- Getter -----------------*/
    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getHaveWarranty() {
        return haveWarranty;
    }

    public String getHaveExpiration() {
        return haveExpiration;
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * Method to get a list of all product info stored in product_map
     * @return lst the list of all product info stored in product_map
     */
    public static List<ProductMap> getAllProductInfoList(){
        List<ProductMap>lst = new ArrayList<>();
        try{
              conn = new DatabaseConnection();
              ResultSet resultSet = conn.executeQuery("SELECT * FROM product_map");
              while(resultSet.next())
                  lst.add(new ProductMap(resultSet));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null)
                conn.close();
            return lst;
        }
    }
}
