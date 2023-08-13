package com.my_web_app.seller.model;

import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Seller extends User {
    private String shopName,shopRoad;
    DatabaseConnection conn = null;

    public Seller() {
    }

    public Seller(long uid) throws  Exception{
        super(uid);
        conn = new DatabaseConnection();
        ResultSet resultSet = conn.executeQuery("SELECT * FROM seller WHERE uid="+uid);
        if(resultSet.next()){
            this.shopName = resultSet.getString("shop_name");
            this.shopRoad = resultSet.getString("shop_road");
        }
    }

    public Seller(String shopName, String shopRoad) {
        this.shopName = shopName;
        this.shopRoad = shopRoad;
    }


    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Seller(final HttpServletRequest request,String role)  {
        super(request,role);
        try{
            this.shopName = request.getParameter("shop-name");
            this.shopRoad = request.getParameter("shop-road");
        } catch (Exception e) {
            throw new RuntimeException(e+" Seller Constructor");
        }
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            conn = new DatabaseConnection();
            // Start the transaction
            conn.setAutoCommit(false);

            super.storeInDatabase(conn);

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO seller(uid,shop_name,shop_road) VALUES(?,?,?)");
            pstmt.setLong(1,getNid());
            pstmt.setString(2,shopName);
            pstmt.setString(3,shopRoad);

            pstmt.execute();

            // If everything has gone well so far, commit the transaction
            conn.commit();


        } catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();

            throw new RuntimeException(e + " Seller ");
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    public void sellProduct(ProductMap productMap, String firstProduct, String lastProduct){

        try{
            DatabaseConnection conn = new DatabaseConnection();
            conn.setAutoCommit(false);

            // update product status in DB
            Product.updateProductStatus(conn, productMap.getTableName(),firstProduct,lastProduct, Utility.productStatusByRole("customer"));
            // update sold date
            Product.updateHandOverDate(conn, productMap.getTableName(), firstProduct, lastProduct);

            // if everything is ok then commit
            conn.commit();

        }catch (Exception e){
            // if there is any error then rollback
            conn.rollback();
            System.err.println(e);
        }finally {
            conn.close();
        }
    }
    public String getShopName() {
        return shopName;
    }

    public String getShopRoad() {
        return shopRoad;
    }

}