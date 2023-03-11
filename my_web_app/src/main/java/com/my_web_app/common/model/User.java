package com.my_web_app.common.model;

import DB.DatabaseConnection;
import com.my_web_app.Utility;
import com.sun.istack.internal.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created on 06-Feb-23
 *
 * @author Nayeem
 */
public class User {
    private long nid;
    private String name, email;
    private Address address;
    private String role;


    public User() {
    }

    public User(String email,String role){
        this.email = email;
        this.role = role;
    }

    public User(long uid){
        DatabaseConnection conn = null;
        try{
            conn = new DatabaseConnection();
            PreparedStatement preparedStatement = conn.getPreparedStatement("SELECT * FROM users WHERE nid=?;");
            preparedStatement.setLong(1,uid);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.nid = rs.getLong("nid");
                this.address = new Address(rs.getString("address_id"));
                this.role = getRole(this.nid);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(conn!=null)
                conn.close();
        }
    }
    /**
     * Constructor
     * @param request the HttpServletRequest
     */
    public User(HttpServletRequest request,String role) {
        try{
            this.nid = Long.parseLong(request.getParameter("nid"));
            this.name = request.getParameter("name");
            this.email = request.getParameter("email");
            this.role=role.toLowerCase();


            System.out.println("Entered!");
            String addId = request.getParameter("division")+""+request.getParameter("district")+request.getParameter("upazila")+request.getParameter("union");
            String division = Address.getDivisionList().get(Integer.parseInt(request.getParameter("division"))-1).getName();
            String district = Address.getDistrictList().get(Integer.parseInt(request.getParameter("district"))-1).getName();
            String upazila = Address.getUpazilaList().get(Integer.parseInt(request.getParameter("upazila"))-1).getName();
            String union = Address.getUnionList().get(Integer.parseInt(request.getParameter("union"))-1).getName();

            this.address = new Address(addId,division,district,upazila,union);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e+" User Constructor");
        }
    }


    /**==============Methods====================*/
    protected void storeInDatabase(DatabaseConnection conn)
    {
        try {
            // store address in address table by Address class
            address.storeInDatabase();

            //store user info in users table
            PreparedStatement pstmt = conn.getPreparedStatement("INSERT INTO users(name,nid,email,address_id) VALUES(?,?,?,?)");
            pstmt.setString(1,this.name);
            pstmt.setLong(2,nid);
            pstmt.setString(3,email);
            pstmt.setString(4,address.getAddrId());

            pstmt.execute();

            // get role id from role table where role_name is equal to this.role
            int roleId = -1;
            ResultSet rs = conn.executeQuery("SELECT role_id FROM role WHERE role_name='"+this.role+"';");
            if(rs.next())
                roleId = rs.getInt("role_id");

            // assign  role to this user by inserting into user_role table
            pstmt = conn.getPreparedStatement("INSERT INTO user_role(role_id,uid) VALUES(?,?);");
            pstmt.setInt(1,roleId);
            pstmt.setLong(2,this.nid);

            pstmt.execute();


        } catch (Exception e) {


            throw new RuntimeException(e + "User");
        }

    }

    /**
     * static method to get user role by uid
     * @param uid the user's nid
     * @return role the user's role
     */
    @NotNull
    public static String getRole(long uid){

        DatabaseConnection conn=null;
        String role = null;
        try{
            conn = new DatabaseConnection();

            ResultSet rs = conn.executeQuery("SELECT role_name from role,user_role WHERE role.role_id=user_role.role_id and user_role.uid="+uid);

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
    public static Boolean hasAccessToProduct(long lastHolder,String tableName,String firstProduct,String lastProduct){
        Boolean res = false;
        DatabaseConnection conn = null;
        try {
            conn = new DatabaseConnection();
            String query = "SELECT COUNT(*) AS x FROM "+tableName+" WHERE last_holder=? AND pid IN "+ Utility.getCommaSeparatedPidList(firstProduct,lastProduct);

            PreparedStatement pstmt = conn.getPreparedStatement(query);
            pstmt.setLong(1,lastHolder);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int x = rs.getInt("x");

            rs = conn.executeQuery("SELECT COUNT(*) AS y FROM "+tableName+" WHERE pid IN "+Utility.getCommaSeparatedPidList(firstProduct,lastProduct));

            rs.next();
            int y = rs.getInt("y");

            if(x!=0 && y!=0 && y-x==0)
                res = true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }
        return res;

    }


    /**------------Getter------------**/
    public long getNid() {
        return nid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
}

