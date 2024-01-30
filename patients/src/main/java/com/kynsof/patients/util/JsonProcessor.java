package com.kynsof.patients.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class JsonProcessor {

    public void processJson(String jsonStr) {
        try {
            // Eliminar las comillas adicionales al principio y al final
            if (jsonStr.startsWith("\"") && jsonStr.endsWith("\"")) {
                jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
            }

            // Reemplazar las barras invertidas de escape y las comillas dobles
            jsonStr = jsonStr.replace("\\\"", "\"");

            JSONObject jsonObject = new JSONObject(jsonStr);

            // Obtener los campos del JSON
            String id = jsonObject.getString("id");
            long timestamp = jsonObject.getLong("date");
            String type = jsonObject.getString("type");

            // Convertir timestamp a Date
            Date date = new Date(timestamp);

            // Obtener el objeto anidado "data"
            JSONObject jsonData = jsonObject.getJSONObject("data");
            int dataId = jsonData.getInt("id");
            String name = jsonData.getString("name");
            String email = jsonData.getString("email");

            // Aquí puedes hacer lo que necesites con estos valores
            System.out.println("ID: " + id);
            System.out.println("Date: " + date);
            System.out.println("Type: " + type);
            System.out.println("Data ID: " + dataId);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
