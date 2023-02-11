package com.example.my_web_app;



import com.example.my_web_app.common.model.Address;

import java.util.List;
import java.util.Map;

public class Check{
    public static void main(String[] args) throws Exception {
            List<Location> map = Address.getDistrictList();


        System.out.println(map);

    }

}