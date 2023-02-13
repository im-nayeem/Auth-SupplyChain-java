package com.my_web_app;



import com.my_web_app.common.model.Address;

import java.util.List;

public class Check{
    public static void main(String[] args) throws Exception {
            List<Location> map = Address.getDistrictList();


        System.out.println(map);

    }

}