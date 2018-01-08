package com.study.payments.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.study.payments.entity.User;
import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;

import java.io.Reader;
import java.util.List;

public class JsonConverter {
    public static String toJson(List<User> list) {
        JsonArray jsonArray = new JsonArray();

        for (User user : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", user.getId());
            jsonObject.addProperty("first_name", user.getFirstName());
            jsonObject.addProperty("last_name", user.getLastName());
            jsonObject.addProperty("payment", user.getPayment());
            jsonArray.add(jsonObject);

        }
        return jsonArray.toString();
    }

    public static User toUser(Reader reader) {
        JsonElement element = new JsonParser().parse(reader);
        JsonObject jsonObject = element.getAsJsonObject();
        User user = new User();

        user.setId(jsonObject.get("id").getAsInt());
        user.setFirstName(jsonObject.get("first_name").getAsString());
        user.setLastName(jsonObject.get("last_name").getAsString());
        user.setPayment(jsonObject.get("payment").getAsDouble());
        System.out.println("Parsed user: " + user);
        return user;
    }
}
