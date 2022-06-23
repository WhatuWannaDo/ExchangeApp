package com.example.exchangeapp.ui.MainMenu

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainMenu(currencyViewModel: CurrencyViewModel){
    GlobalScope.launch (Dispatchers.IO) {
        currencyViewModel.addCurrency(CurrencyModel(0,"test", 10.0))
    }
}