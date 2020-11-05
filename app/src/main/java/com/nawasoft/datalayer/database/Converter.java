package com.nawasoft.datalayer.database;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nawasoft.datalayer.model.MyTicket;

import java.util.Collections;
import java.util.List;

public class Converter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<MyTicket> stringToCards(String data) {
        if (data == null)
            return Collections.emptyList();

        return gson.fromJson(data, new TypeToken<List<MyTicket>>(){}.getType());
    }

    @TypeConverter
    public static String cardsToString(List<MyTicket> cards) {
        return gson.toJson(cards);
    }
}
