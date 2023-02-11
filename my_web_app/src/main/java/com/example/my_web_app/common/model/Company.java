package com.example.my_web_app.common.model;

import DB.DatabaseConnection;

import java.sql.ResultSet;

public class Company {
    private String name,supplierCode,sellerCode,distributorCode;
    private int employeeCount;
    public Company()
    {
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            ResultSet rs = conn.executeQuery("SELECT * FROM company_info;");
            rs.next();
            name = rs.getString("name");
            employeeCount = rs.getInt("employee_count");
            supplierCode = rs.getString("supplier_code");
            distributorCode = rs.getString("distributor_code");
            sellerCode = rs.getString("seller_code");
        } catch (Exception e) {
            throw new RuntimeException(e+" \nCompany");
        }
        finally {
            conn.close();
        }
    }

    public String getName() {
        return name;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }
}
