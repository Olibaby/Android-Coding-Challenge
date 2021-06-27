package com.example.androidcodingchallenge.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.androidcodingchallenge.base.BaseDao
import com.example.androidcodingchallenge.model.response.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao: BaseDao<Card> {
    @Query("select * from card")
    fun getAllCards() : Flow<List<Card>>

    @Query("delete from card")
    fun deleteCards()

    @Transaction
    fun updateCards(listOfCards: List<Card>){
        deleteCards()
        insertAll(listOfCards)
    }
}