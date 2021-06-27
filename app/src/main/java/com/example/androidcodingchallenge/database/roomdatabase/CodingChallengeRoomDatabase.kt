package com.example.androidcodingchallenge.database.roomdatabase

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidcodingchallenge.database.converter.CardConverter
import com.example.androidcodingchallenge.database.dao.CardDao
import com.example.androidcodingchallenge.model.response.Card

@Database(entities = [Card::class], version = 4,exportSchema = false)
@TypeConverters(CardConverter::class )
abstract class CodingChallengeRoomDatabase: RoomDatabase()  {
    abstract fun cardDao(): CardDao
}

