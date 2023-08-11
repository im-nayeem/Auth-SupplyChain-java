package com.my_web_app.distributorAgent;


import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

public class DistributorAgent extends User {
    DatabaseConnection conn = null;

    public DistributorAgent() {
    }
    public DistributorAgent(long uid) throws  Exception{
        super(uid);

    }

    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public DistributorAgent(final HttpServletRequest request, String role)  {
        super(request,role);
    }
    /**=================Methods====================*/
    public void storeInDatabase(){
        try {
            conn = new DatabaseConnection();
            // start the transaction
            conn.setAutoCommit(false);

            super.storeInDatabase(conn);


            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO distributorAgent(uid) VALUES(?)");
            pstmt.setLong(1,getNid());

            pstmt.execute();

            // If everything has gone well so far, commit the transaction
            conn.commit();


        } catch (Exception e) {
            // If there's an error during the transaction, rollback the changes
            if (conn != null) {
                conn.rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }

    public void updateProductSeller(DatabaseConnection conn, String tableName,String firstProduct,String lastProduct,long seller){
        try{

            String query = "UPDATE "+tableName+" SET seller=? WHERE pid IN "+ Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1,seller);
            preparedStatement.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void distributeProductToSeller(ProductMap productMap, String firstProduct, String lastProduct, User seller){
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);

            // update product holder(last holder)
            Product.updateProductHolder(conn, productMap.getTableName(),firstProduct,lastProduct,seller.getNid());

            // update product status (Distributed)
            Product.updateProductStatus(conn, productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole(seller.getRole()));

            // update handover date
            Product.updateHandOverDate(conn, productMap.getTableName(),firstProduct,lastProduct);

            // map product affiliation with distributor for further tracking of history
            productMap.setProductAffiliation(seller.getNid());

            // update product seller associated with the product
            updateProductSeller(conn, productMap.getTableName(),firstProduct,lastProduct,seller.getNid());

            conn.commit();

        } catch (Exception e) {
            if (conn != null)
                conn.rollback();
        }finally {
            if(conn != null)
                conn.close();
        }
    }

}