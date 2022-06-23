package com.example.exchangeapp.domain.Repository

import com.example.exchangeapp.data.DataBase.FavoriteCurrencyDAO
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.data.Repository.CurrencyRepositoryInt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(private val favoriteCurrencyDAO: FavoriteCurrencyDAO) : CurrencyRepositoryInt {

    override val getAllCurrencies: Flow<List<CurrencyModel>> = favoriteCurrencyDAO.getAllFavoriteCurrencies()

    override suspend fun addCurrency(currencyModel: CurrencyModel) {
        return favoriteCurrencyDAO.addToFavoriteCurrencies(currencyModel)
    }

}