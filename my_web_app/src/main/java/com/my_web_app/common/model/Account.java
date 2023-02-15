package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.SecurePassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Account {
    private Long uid;
    private String email;
    private String password;
    private  String hashKey;
    private String salt;
    DatabaseConnection conn = null;


    /**
     * Constructor to retrieve data
     * @param uid the user's id or nid to retrieve data
     */
    public Account(Long uid){
        try{


            conn = new DatabaseConnection();

            PreparedStatement preparedStatement = conn.getPreparedStatement("SELECT * FROM account WHERE uid=?");
            preparedStatement.setLong(1,uid);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.email = resultSet.getString("email");
                this.hashKey = resultSet.getString("hash_key");
                this.salt = resultSet.getString("salt");
                this.uid = resultSet.getLong("uid");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Constructor to retrieve data
     * @param email the user's email to retrieve data
     */
    public Account(String email){
        try{
            conn = new DatabaseConnection();

            PreparedStatement preparedStatement = conn.getPreparedStatement("SELECT * FROM account WHERE email=?");
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.email = resultSet.getString("email");
                this.hashKey = resultSet.getString("hash_key");
                this.salt = resultSet.getString("salt");
                this.uid = resultSet.getLong("uid");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor to store data
     * @param uid the user's nid
     * @param email user email
     * @param password user provided password
     */
    public Account(Long uid,String email,String password){
        try{
            this.uid = uid;
            this.email = email;
            this.password = password;
            this.salt = SecurePassword.generateSalt(500).get();
            this.hashKey = SecurePassword.hashPassword(password,this.salt).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**=================Methods======================**/

    /**
     * Method to store account info in database
     */
    public void storeInDatabase(){
        try{
            conn = new DatabaseConnection();

            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO account(uid,email,hash_key,salt) VALUES(?,?,?,?);");

            pstmt.setLong(1,this.uid);
            pstmt.setString(2,this.email);
            pstmt.setString(3,this.hashKey);
            pstmt.setString(4,this.salt);

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(){
        try{
            conn = new DatabaseConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }


    /**
     * verifyAccount Method to verify user provided password with hashKey in database during login
     * @param password the password entered while login
     * @return Boolean value whether the password is matched with the hashKey stored in DB generated from previously provided password
     */

    public Boolean verifyAccount(String password){
        return SecurePassword.verifyPassword(password,this.hashKey,this.salt);
    }



    public String getRole(){
        String role=null;
        try{
            conn = new DatabaseConnection();

            ResultSet rs = conn.executeQuery("SELECT role_name from role,user_role WHERE role.role_id=user_role.role_id and user_role.uid="+this.uid);

            if(rs.next())
                role = rs.getString("role_name");
        } catch (Exception e) {
            throw new RuntimeException(e + "getRole");
        }
        finally {
            if(conn!=null)
                conn.close();
            return role;
        }
    }

    /**----------------------Getters----------------------**/

    public Long getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
