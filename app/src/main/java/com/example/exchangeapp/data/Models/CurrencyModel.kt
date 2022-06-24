package com.example.exchangeapp.data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_currencies")
data class CurrencyModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val selectedCurrency : String,
    val currency : String,
    val amount : String
)
