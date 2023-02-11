package com.example.my_web_app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class DivisionInfo {
    public static void main(String[] args) throws Exception {
        URL divisionsURL = new URL("https://raw.githubusercontent.com/nuhil/bangladesh-geocode/master/divisions/divisions.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(divisionsURL.openStream()));

        StringBuilder divisionsJSON = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            divisionsJSON.append(inputLine);
        }
        in.close();

        JSONArray divisions = new JSONArray(divisionsJSON.toString());
        for (int i = 0; i < divisions.length(); i++) {
            JSONObject division = divisions.getJSONObject(i);
            String divisionName = division.getString("name");
            String divisionURL = division.getString("url");
            System.out.println("Division name: " + divisionName + " URL: " + divisionURL);
        }
    }
}
