package com.example.exchangeapp.domain.Repository

import com.example.exchangeapp.data.DataBase.FavoriteCurrencyDAO
import com.example.exchangeapp.data.Models.CurrenciesModelResponse
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.data.Network.CurrenciesAPI
import com.example.exchangeapp.data.Repository.CurrencyRepositoryInt
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(private val favoriteCurrencyDAO: FavoriteCurrencyDAO, private val currenciesAPI: CurrenciesAPI) : CurrencyRepositoryInt {

    override val getAllCurrencies: Flow<List<CurrencyModel>> = favoriteCurrencyDAO.getAllFavoriteCurrencies()

    override suspend fun addCurrency(currencyModel: CurrencyModel) {
        return favoriteCurrencyDAO.addToFavoriteCurrencies(currencyModel)
    }

    override suspend fun getCurrencies(base : String): Response<CurrenciesModelResponse> {
        return currenciesAPI.getCurrencies(base)
    }


}