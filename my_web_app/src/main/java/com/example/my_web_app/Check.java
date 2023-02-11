package com.example.my_web_app;



import com.example.my_web_app.common.model.Address;
import java.util.Map;

public class Check{
    public static void main(String[] args) throws Exception {
            Map<Integer,String>map = Address.getDivisionList();

        System.out.println(map);


    }

}