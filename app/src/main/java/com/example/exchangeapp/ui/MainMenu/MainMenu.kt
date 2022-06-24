package com.example.exchangeapp.ui.MainMenu


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.exchangeapp.ui.MainActivity
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import com.example.exchangeapp.ui.screens.Screens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(currencyViewModel: CurrencyViewModel, navController: NavController, activity: MainActivity){
    var dropDownMenuCurrenciesState = mutableStateOf(false)
    Scaffold(topBar = { TopAppBarMainMenu(currencyViewModel = currencyViewModel,navController = navController, activity, dropDownMenuCurrenciesState)}) {
        Column() {
            Text(text = "SSSSS")
        }
    }
    DropDownMenuCurrencies(currencyViewModel = currencyViewModel, activity = activity, dropDownMenuCurrenciesState)
}

@Composable
fun TopAppBarMainMenu(currencyViewModel: CurrencyViewModel, navController: NavController, activity: MainActivity, dropDownMenuState : MutableState<Boolean>){
    SmallTopAppBar(
        title = { Text(text = "Выбор валюты", modifier = Modifier.clickable {
            dropDownMenuState.value = true
        })},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        actions = {
            Button(onClick = {
                navController.navigate(Screens.FavoriteCurrencies.route)
            }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "toFavorite")
            }
            Button(onClick = {
                navController.navigate(Screens.Settings.route)
            }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "toSettings")
            }
        })
}

@Composable
fun DropDownMenuCurrencies(currencyViewModel: CurrencyViewModel, activity: MainActivity, dropDownMenuState: MutableState<Boolean>){
    val currenciesList = listOf(
    "AED",
    "AFN",
    "ALL",
    "AMD",
    "ANG",
    "AOA",
    "ARS",
    "AUD",
    "AWG",
    "AZN",
    "BAM",
    "BBD",
    "BDT",
    "BGN",
    "BHD",
    "BIF",
    "BMD",
    "BND",
    "BOB",
    "BRL",
    "BSD",
    "BTC",
    "BTN",
    "BWP",
    "BYN",
    "BYR",
    "BZD",
    "CAD",
    "CDF",
    "CHF",
    "CLF",
    "CLP",
    "CNY",
    "COP",
    "CRC",
    "CUC",
    "CUP",
    "CVE",
    "CZK",
    "DJF",
    "DKK",
    "DOP",
    "DZD",
    "EGP",
    "ERN",
    "ETB",
    "EUR",
    "FJD",
    "FKP",
    "GBP",
    "GEL",
    "GGP",
    "GHS",
    "GIP",
    "GMD",
    "GNF",
    "GTQ",
    "GYD",
    "HKD",
    "HNL",
    "HRK",
    "HTG",
    "HUF",
    "IDR",
    "ILS",
    "IMP",
    "INR",
    "IQD",
    "IRR",
    "ISK",
    "JEP",
    "JMD",
    "JOD",
    "JPY",
    "KES",
    "KGS",
    "KHR",
    "KMF",
    "KPW",
    "KRW",
    "KWD",
    "KYD",
    "KZT",
    "LAK",
    "LBP",
    "LKR",
    "LRD",
    "LSL",
    "LTL",
    "LVL",
    "LYD",
    "MAD",
    "MDL",
    "MGA",
    "MKD",
    "MMK",
    "MNT",
    "MOP",
    "MRO",
    "MUR",
    "MVR",
    "MWK",
    "MXN",
    "MYR",
    "MZN",
    "NAD",
    "NGN",
    "NIO",
    "NOK",
    "NPR",
    "NZD",
    "OMR",
    "PAB",
    "PEN",
    "PGK",
    "PHP",
    "PKR",
    "PLN",
    "PYG",
    "QAR",
    "RON",
    "RSD",
    "RUB",
    "RWF",
    "SAR",
    "SBD",
    "SCR",
    "SDG",
    "SEK",
    "SGD",
    "SHP",
    "SLL",
    "SOS",
    "SRD",
    "STD",
    "SVC",
    "SYP",
    "SZL",
    "THB",
    "TJS",
    "TMT",
    "TND",
    "TOP",
    "TRY",
    "TTD",
    "TWD",
    "TZS",
    "UAH",
    "UGX",
    "USD",
    "UYU",
    "UZS",
    "VEF",
    "VND",
    "VUV",
    "WST",
    "XAF",
    "XAG",
    "XAU",
    "XCD",
    "XDR",
    "XOF",
    "XPF",
    "YER",
    "ZAR",
    "ZMK",
    "ZMW",
    "ZWL"
    )
    DropdownMenu(expanded = dropDownMenuState.value, onDismissRequest = { dropDownMenuState.value = false }) {
        currenciesList.forEach { label ->
            DropdownMenuItem(onClick = {
                Log.e("clicked", label)
                currencyViewModel.getCurrencies(label)
                currencyViewModel.currencyResponse.observe(activity, Observer {
                    if (it.isSuccessful){
                        Log.e("TEST", it.body().toString())
                    }else{
                        Log.e("TEST", "NOT_SUCCESS")
                    }
                })
                dropDownMenuState.value = false
            }, text = { Text(text = label)})
        }
    }
}