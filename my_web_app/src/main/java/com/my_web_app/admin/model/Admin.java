package com.my_web_app.admin.model;

import DB.DatabaseConnection;
import com.my_web_app.SecurePassword;

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
}
