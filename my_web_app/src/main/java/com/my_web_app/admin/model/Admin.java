package com.my_web_app.admin.model;

import DB.DatabaseConnection;
import com.my_web_app.SecurePassword;
import com.my_web_app.Utility;

import javax.rmi.CORBA.Util;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public void updateProductDistributor(String tableName,String firstProduct,String lastProduct,long distributor){
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            conn.setAutoCommit(false);
            String query = "UPDATE "+tableName+" SET distributor=? WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct);
            PreparedStatement preparedStatement = conn.getPreparedStatement(query);
            preparedStatement.setLong(1,distributor);
            preparedStatement.executeUpdate();
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
