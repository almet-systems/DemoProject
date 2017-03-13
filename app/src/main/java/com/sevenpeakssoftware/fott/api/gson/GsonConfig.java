package com.sevenpeakssoftware.fott.api.gson;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by razir on 1/3/2017.
 */

public class GsonConfig {
    public static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy hh:mm");

    private static JsonDeserializer<DateTime> dateDeser = (json, typeOfT, context) -> {
        if (json == null) {
            return null;
        } else {
            if (!json.isJsonNull()) {
                try {
                    return formatter.parseDateTime(json.getAsString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            } else {
                return null;
            }
        }
    };

    public static GsonBuilder getBuilder() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, dateDeser);

        return builder;
    }

    public static Gson buildDefaultJson() {
        Gson gson = getBuilder()
                .create();
        return gson;
    }

}
