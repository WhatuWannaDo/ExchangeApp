package com.example.exchangeapp.data.Repository

import com.example.exchangeapp.data.Models.CurrencyModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepositoryInt {
    val getAllCurrencies : Flow<List<CurrencyModel>>

    suspend fun addCurrency(currencyModel: CurrencyModel)
}