package com.example.exchangeapp.data.DI

import com.example.exchangeapp.data.DataBase.FavoriteCurrencyDAO
import com.example.exchangeapp.data.Network.CurrenciesAPI
import com.example.exchangeapp.data.Repository.CurrencyRepositoryInt
import com.example.exchangeapp.domain.Repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrenciesRepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(favoriteCurrencyDAO: FavoriteCurrencyDAO, currenciesAPI: CurrenciesAPI) : CurrencyRepositoryInt = CurrencyRepository(favoriteCurrencyDAO, currenciesAPI)
}