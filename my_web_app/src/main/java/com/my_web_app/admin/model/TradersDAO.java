package com.my_web_app.admin.model;

import DB.DatabaseConnection;
import com.my_web_app.common.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12-Aug-23
 *
 * @author Nayeem
 */
public class TradersDAO {
    private List<User> traders;
    private DatabaseConnection conn = null;

    public TradersDAO() {
        try{
            conn = new DatabaseConnection();
            ResultSet resultSet = conn.executeQuery("SELECT * FROM users");
            traders = new ArrayList<>();
            while(resultSet.next()){
                traders.add(new User(resultSet));
            }

        }catch (Exception e){
            System.err.println(e);
        }
        finally {
            conn.close();
        }
    }

    public List<User> getTraders() {
        return traders;
    }
}
