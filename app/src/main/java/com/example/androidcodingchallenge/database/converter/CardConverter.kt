package com.example.androidcodingchallenge.database.converter

import androidx.room.TypeConverter
import com.example.androidcodingchallenge.model.response.Card
import com.example.androidcodingchallenge.model.response.CardX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CardConverter{
    @TypeConverter
    fun fromString(value: String): CardX {
        val listType = object : TypeToken<CardX>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromAccountList(list: CardX): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}