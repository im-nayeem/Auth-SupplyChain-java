package com.my_web_app.common.model;

public class Account {
    private int uid;
    private String email;
    private String password;
    private  String hashKey;
    private String salt;



    public Account(int uid){

    }
    public Account(String email){

    }
    public Account(String email,String password){

    }


    /**=================Methods======================**/
    public void storeInDatabase(){

    }

    public Boolean verifyAccount(){
        return  false;
    }

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
