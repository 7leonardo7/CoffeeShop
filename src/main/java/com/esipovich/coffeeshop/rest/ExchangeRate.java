package com.esipovich.coffeeshop.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRate {

    private static final String GET_URL =
            "http://www.nbrb.by/API/ExRates/Rates/145";

    //можно ли вытянуть значение по имени поля
    public static String getExchangeRate(){
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(GET_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                reader.close();

            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return response.substring(response.lastIndexOf(":") + 1, response.length() - 1);
    }

}
