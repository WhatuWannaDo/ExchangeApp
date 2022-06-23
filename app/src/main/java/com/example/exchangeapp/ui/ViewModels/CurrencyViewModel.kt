package com.example.exchangeapp.ui.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.exchangeapp.data.DataBase.DataBase
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.domain.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(application: Application) : AndroidViewModel(application){

    private val currencyRepository : CurrencyRepository
    val getAllCurrencies : Flow<List<CurrencyModel>>


    init {
        val currencyDAO = DataBase.getDataBase(application).favoriteCurrencyDAO()
        currencyRepository = CurrencyRepository(currencyDAO)
        getAllCurrencies = currencyRepository.getAllCurrencies
    }

    suspend fun addCurrency(currencyModel: CurrencyModel){
        viewModelScope.launch(Dispatchers.IO) {
            currencyRepository.addCurrency(currencyModel)
        }
    }

    class CurrencyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
                return CurrencyViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}