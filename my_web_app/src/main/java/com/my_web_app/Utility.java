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

    /**
     * Method to extract product code from product id(pid)
     * @param Id the product id
     * @return the product code
     */
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


    /**
     * Method to extract integer part of the product id
     * @param pid the product id
     * @return the integer part of product id
     */
    public static int extractIntFromPid(String pid) {
        String intPart = pid.replaceAll("[^\\d]", "");
        return Integer.parseInt(intPart);
    }


    /**
     * @param firstProduct the first product id
     * @param lastProduct the last product id
     * @return comma separated pid of products from first and last product id(e.g, 'HT0,HT1,HT2')
     */
    public static String getCommaSeparatedPidList(String firstProduct,String lastProduct)
    {
        int first = extractIntFromPid(firstProduct);
        int last = extractIntFromPid(lastProduct);
        StringBuilder pidSequence= new StringBuilder("(");
        String productCode=getCode(firstProduct);
        for(int i=first;i<last;i++)
            pidSequence.append("'").append(productCode).append(i).append("',");
        pidSequence.append("'").append(productCode).append(last).append("')");

        return pidSequence.toString();
    }


    /**
     * Method to get the IP address of the host server
     * @return the host IP address
     */
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
     * Method to get the list of geolocation(district, upazila, union) by parsing json file
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

            for (Object o : arr) {
                JSONObject location = (JSONObject) o;

                String name = (String) location.get("name");
                int id = Integer.parseInt((String) location.get("id"));
                int parentId = -1;
                if (location.getOrDefault("division_id", null) != null) {
                    parentId = Integer.parseInt((String) location.get("division_id"));
                } else if (location.getOrDefault("district_id", null) != null) {
                    parentId = Integer.parseInt((String) location.get("district_id"));

                } else if (location.getOrDefault("upazilla_id", null) != null) {
                    parentId = Integer.parseInt((String) location.get("upazilla_id"));

                }

                map.add(new Location(name, id, parentId));
            }
            return  map;

        }
    }


    public  static  int getRandomCode(){
        return   ThreadLocalRandom.current().nextInt(11111, 10000000 );
    }


    /**
     * Method to get the product status according to the product holder
     * @param holder the current holder having the product
     * @return the product status
     */
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

    /**
     * Method to get comma separated table names from product code list
     * @param productCodeList the array of product codes
     * @return s the comma separated string of table names
     */
    public static String commaSeparatedTablesFromProductCodes(List<String> productCodeList){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < productCodeList.size(); i++){

            if(i == productCodeList.size() - 1)
                s.append("table_").append(productCodeList.get(i).toLowerCase()).append(" ");
            else
                s.append("table_").append(productCodeList.get(i).toLowerCase()).append(", ");
        }

        return s.toString();
    }
}
