package com.example.exchangeapp.data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangeapp.data.Models.CurrencyModel

@Database(entities = [CurrencyModel::class], exportSchema = false, version = 3)
abstract class DataBase : RoomDatabase() {
    abstract fun favoriteCurrencyDAO() : FavoriteCurrencyDAO

    companion object{
        @Volatile
        private var INSTANCE : DataBase? = null

        fun getDataBase(context : Context) : DataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context, DataBase::class.java, "CurrenciesDataBase")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}