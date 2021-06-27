package com.example.androidcodingchallenge.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val card: CardX,
    val card_type: String
)