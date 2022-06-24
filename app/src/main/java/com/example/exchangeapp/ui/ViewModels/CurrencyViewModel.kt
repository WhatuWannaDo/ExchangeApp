package com.example.exchangeapp.ui.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.exchangeapp.data.DI.CurrenciesAPIModule
import com.example.exchangeapp.data.DataBase.DataBase
import com.example.exchangeapp.data.Models.CurrenciesModelResponse
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.domain.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(application: Application) : AndroidViewModel(application){

    private val currencyRepository : CurrencyRepository
    val getAllCurrencies : Flow<List<CurrencyModel>>
    var currencyResponse: MutableLiveData<Response<CurrenciesModelResponse>> = MutableLiveData()


    init {
        val currencyDAO = DataBase.getDataBase(application).favoriteCurrencyDAO()
        val currenciesAPI = CurrenciesAPIModule.provideCurrenciesApi(CurrenciesAPIModule.provideRetrofit())
        currencyRepository = CurrencyRepository(currencyDAO, currenciesAPI)
        getAllCurrencies = currencyRepository.getAllCurrencies
    }

    suspend fun addCurrency(currencyModel: CurrencyModel){
        viewModelScope.launch(Dispatchers.IO) {
            currencyRepository.addCurrency(currencyModel)
        }
    }

    fun getCurrencies(base : String){
        viewModelScope.launch{
            val response = currencyRepository.getCurrencies(base)
            currencyResponse.value = response
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