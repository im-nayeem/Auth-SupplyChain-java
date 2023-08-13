package com.my_web_app.distributor.model;


import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Distributor extends User {
    private String distCenterRoad;
    DatabaseConnection conn = null;

    public Distributor() {
    }


    public Distributor(String distCenterRoad) {
        this.distCenterRoad = distCenterRoad;
    }

    /**
     * Constructor to retrieve data
     * @param uid the user's nid
     * @throws Exception
     */
    public Distributor(long uid) throws  Exception{
        super(uid);
        conn = new DatabaseConnection();
        ResultSet resultSet = conn.executeQuery("SELECT * FROM seller WHERE uid="+uid);
        if(resultSet.next()){
            this.distCenterRoad = resultSet.getString("dist_center_road");
        }
    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public Distributor(final HttpServletRequest request, String role)  {
        super(request,role);
        try{
            this.distCenterRoad = request.getParameter("dcenter-road");
        } catch (Exception e) {
            throw new RuntimeException(e+" Distributor Constructor");
        }
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            conn = new DatabaseConnection();
            // start the transaction
            conn.setAutoCommit(false);

            super.storeInDatabase(conn);

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO distributor(uid,dist_center_road) VALUES(?,?)");
            pstmt.setLong(1,getNid());
            pstmt.setString(2, distCenterRoad);

            pstmt.execute();

            // If everything has gone well so far, commit the transaction
            conn.commit();


        } catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    private void updateProductDistributorAgent(DatabaseConnection conn, ProductMap productMap, String firstProduct, String lastProduct, long distributorNid){
        try {
            String query = "UPDATE "+productMap.getTableName()+" SET distributor_agent=? WHERE pid IN "+ Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1,distributorNid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Method to distribute product to distributorAgent **/
    public void distributeProduct(ProductMap productMap, String firstProduct, String lastProduct, User distributorAgent){
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);

            // update product holder(last holder)
            Product.updateProductHolder(conn, productMap.getTableName(),firstProduct,lastProduct,distributorAgent.getNid());

            // update product status (distributing)
            Product.updateProductStatus(conn, productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole(distributorAgent.getRole()));

            // update handover date
            Product.updateHandOverDate(conn, productMap.getTableName(),firstProduct,lastProduct);

            // map product affiliation with distributor for further tracking of history
            productMap.setProductAffiliation(distributorAgent.getNid());

            // update distributorAgent associated with the product
            updateProductDistributorAgent(conn, productMap, firstProduct, lastProduct, distributorAgent.getNid());

            // If everything gone well then commit
            conn.commit();

        }catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();

            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    public String getDistCenterRoad() {
        return distCenterRoad;
    }

}