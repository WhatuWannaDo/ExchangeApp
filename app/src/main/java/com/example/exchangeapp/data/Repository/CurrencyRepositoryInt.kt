package com.example.exchangeapp.data.Repository

import com.example.exchangeapp.data.Models.CurrenciesModelResponse
import com.example.exchangeapp.data.Models.CurrencyModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CurrencyRepositoryInt {
    val getAllCurrencies : Flow<List<CurrencyModel>>

    suspend fun addCurrency(currencyModel: CurrencyModel)

    suspend fun getCurrencies(base : String) : Response<CurrenciesModelResponse>
}