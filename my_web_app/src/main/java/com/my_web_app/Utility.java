package com.my_web_app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 31-Jan-23
 *
 * @author Nayeem
 */
public class Utility {
    public static String getInitialQuery()
    {
        return
                "CREATE TABLE admin(\n" +
                "    email varchar(30) UNIQUE,\n" +
                "    hash_key varchar(500),\n" +
                "    salt varchar(500),\n" +
                "    id int not null AUTO_INCREMENT PRIMARY KEY\n" +
                "    );\n" +
                " CREATE table address(\n" +
                "     address_id varchar(15) not null PRIMARY KEY,\n" +
                "     division varchar(20),\n" +
                "     district varchar(20),\n" +
                "     upazila varchar(30),\n" +
                "     uniion varchar(30)\n" +
                "     );\n" +
                " CREATE INDEX idx_address_id ON address(address_id);\n" +

                "CREATE TABLE role(\n" +
                "    role_id int not null AUTO_INCREMENT PRIMARY KEY,\n" +
                "    role_name varchar(15));"+
                "INSERT INTO role(role_name) VALUES('seller');\n" +
                "INSERT INTO role(role_name) VALUES('distributor');\n" +
                "INSERT INTO role(role_name) VALUES('distributorAgent');"+

                " CREATE TABLE users(\n" +
                "     nid int PRIMARY KEY,\n" +
                "     name varchar(30),\n" +
                "     email varchar(30) UNIQUE,\n" +
                "     address_id varchar(15),\n" +
                "     FOREIGN KEY(address_id) REFERENCES address(address_id)\n" +
                "     );\n" +
                " CREATE INDEX idx_users_id on users(nid);\n" +

                "CREATE TABLE user_role(\n" +
                "     uid int,\n" +
                "     role_id int,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid),\n" +
                "     FOREIGN key(role_id) REFERENCES role(role_id)\n" +
                "     );\n"+

                "  CREATE TABLE account(\n" +
                "     acc_id int not null AUTO_INCREMENT PRIMARY KEY,\n" +
                "     email varchar(30) not null,\n" +
                "     hash_key varchar(1000) not null,\n" +
                "     salt varchar(1000) not null,\n" +
                "     uid int not null,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid),\n" +
                "     FOREIGN KEY(email) REFERENCES users(email) on UPDATE CASCADE\n" +
                "     );\n" +
                "  CREATE index idx_account_uid on account(uid);\n" +

                " CREATE TABLE distributor(\n" +
                "     uid int,\n" +
                "     dist_center_road varchar(50),\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE index idx_distributor on distributor(uid);\n" +

                " CREATE TABLE distributor_agent(\n" +
                "     uid int,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE index idx_d_agent on distributor_agent(uid);\n" +

                "  CREATE TABLE seller(\n" +
                "     uid int,\n" +
                "     shop_name varchar(30),\n" +
                "     shop_road varchar(30),\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE INDEX idx_seller on seller(uid);\n" +

                " CREATE TABLE product_map(\n" +
                "     name varchar(100),\n" +
                "     p_code varchar(5) PRIMARY KEY,\n" +
                "     have_warranty varchar(3),\n" +
                "     have_expiration varchar(3),\n" +
                "     table_name varchar(20)\n" +
                "     );\n" +
                " CREATE index idx_product_map on product_map(p_code);\n" +

                " CREATE TABLE batch(\n" +
                "     batch_id varchar(15) PRIMARY KEY,\n" +
                "     total_product int,\n" +
                "     p_code varchar(5),\n" +
                "     manufac_date date,\n" +
                "     exp_date date,\n" +
                "     warranty_year int,\n" +
                "     warranty_month int,\n" +
                "     manufactured int(1) default 0,\n" +
                "     FOREIGN KEY(p_code) REFERENCES product_map(p_code)\n" +
                "     );\n" +
                " CREATE INDEX idx_batch on batch(batch_id);\n" +
                " CREATE INDEX idx_batch_p_code on batch(p_code);\n" +

                "CREATE TABLE company_info (\n" +
                "  name VARCHAR(255) NOT NULL,\n" +
                "  address VARCHAR(255) NOT NULL,\n" +
                "  phone_number VARCHAR(20) NOT NULL,\n" +
                "  email VARCHAR(255) NOT NULL,\n" +
                "  description TEXT NOT NULL,\n" +
                "  logo_url VARCHAR(255)\n" +
                ");";
    }

    public static String getCode(String Id)
    {
        int i;
        for(i=0;i<Id.length();i++)
        {
            if(Character.isDigit(Id.charAt(i)))
                break;
        }
        return Id.substring(0,i);
    }
    public static int extractIntFromPid(String pid) {
        String intPart = pid.replaceAll("[^\\d]", "");
        return Integer.parseInt(intPart);
    }
    public static String getCommaSeparatedPidList(String firstProduct,String lastProduct)
    {
        int first = extractIntFromPid(firstProduct);
        int last = extractIntFromPid(lastProduct);
        String pidSequence="(";
        String productCode=getCode(firstProduct);
        for(int i=first;i<last;i++)
            pidSequence+="'"+(productCode+i)+"',";
        pidSequence+="'"+(productCode+last)+"')";

        return pidSequence;
    }
    public static String getIp()
    {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLinkLocalAddress() && !address.isLoopbackAddress() && address.getHostAddress().indexOf(":") == -1) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method getGeoList
     * @param url the url of json to get list of location
     * @return map the list of location
     * @throws Exception
     */
    public static List<Location> getGeoList(URL url) throws Exception{

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONArray data_obj = (JSONArray) parse.parse(inline);

            JSONObject obj = (JSONObject) data_obj.get(2);
            JSONArray arr = (JSONArray) obj.get("data");

            List<Location> map = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                JSONObject location = (JSONObject) arr.get(i);
//                    System.out.println(division);
                String name = (String) location.get("name");
                int id = Integer.parseInt((String) location.get("id"));
                int parentId=-1;
                if(location.getOrDefault("division_id",null)!=null)
                {
                    parentId = Integer.parseInt((String) location.get("division_id"));
                }
                else if(location.getOrDefault("district_id",null)!=null)
                {
                    parentId = Integer.parseInt((String) location.get("district_id"));

                }
                else if(location.getOrDefault("upazilla_id",null)!=null)
                {
                    parentId = Integer.parseInt((String) location.get("upazilla_id"));

                }

                map.add(new Location(name,id,parentId));
            }
            return  map;

        }
    }

    public  static  int getRandomCode(){
        return   ThreadLocalRandom.current().nextInt(11111, 10000000 );
    }

    public static String productStatusByRole(String holder){

        String status="";
        if(holder.equals("seller"))
            status = "distributed";
        else if(holder.equals("distributorAgent"))
            status =  "distributing";
        else if(holder.equals("customer"))
            status = "sold";
        else if(holder.equals("distributor"))
            status = "supplied";
        else if(holder.equals("admin"))
            status = "manufactured";

        return  status;
    }
}
