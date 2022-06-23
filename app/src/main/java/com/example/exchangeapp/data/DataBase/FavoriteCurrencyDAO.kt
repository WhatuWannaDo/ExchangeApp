package com.example.exchangeapp.data.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangeapp.data.Models.CurrencyModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCurrencyDAO {

    @Query("SELECT * FROM favorite_currencies")
    fun getAllFavoriteCurrencies() : Flow<List<CurrencyModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFavoriteCurrencies(currencyModel: CurrencyModel)

}