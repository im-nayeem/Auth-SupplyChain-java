package com.my_web_app.admin.model;

import DB.DatabaseConnection;
import com.my_web_app.SecurePassword;
import com.my_web_app.Utility;
import com.my_web_app.common.model.Product;
import com.my_web_app.common.model.ProductMap;
import com.my_web_app.common.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 10-Mar-23
 *
 * @author Nayeem
 */
public class Admin {
    private String email;
    private String hashKey;
    private  String salt;

    public Admin(String email){
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            PreparedStatement preparedStatement = conn.getPreparedStatement("SELECT * FROM admin WHERE email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                this.email = resultSet.getString("email");
                this.hashKey = resultSet.getString("hash_key");
                this.salt = resultSet.getString("salt");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmail() {
        return email;
    }

    public Boolean verifyAdmin(String password){
        return SecurePassword.verifyPassword(password,this.hashKey,this.salt);
    }

    public static void setUpDB(){
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            PreparedStatement preparedStatement = conn.getPreparedStatement(Utility.getInitialQuery());
            preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private  void updateProductDistributor(DatabaseConnection conn, ProductMap productMap,String firstProduct, String lastProduct, long distributorNid){
        try{
            String query = "UPDATE "+productMap.getTableName()+" SET distributor=? WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);
            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1,distributorNid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Method for supplying product to distributor **/
    public void supplyProduct(ProductMap productMap, String firstProduct, String lastProduct, User distributor){
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);

            // update product holder(last holder)
            Product.updateProductHolder(conn, productMap.getTableName(),firstProduct,lastProduct,distributor.getNid());

            // update product status (supplied)
            Product.updateProductStatus(conn, productMap.getTableName(),firstProduct,lastProduct,Utility.productStatusByRole(distributor.getRole()));

            // update sold date
            Product.updateHandOverDate(conn, productMap.getTableName(),firstProduct,lastProduct);

            // update product's distributor to keep record for tracking history
            updateProductDistributor(conn, productMap, firstProduct, lastProduct, distributor.getNid());

            // map product affiliation with distributor for further tracking of history
            productMap.setProductAffiliation(distributor.getNid());

            // if everything is gone well then commit
            conn.commit();

        }catch (Exception e) {
//             If there's an error during the transaction, rollback the changes
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



}
