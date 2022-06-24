package com.example.exchangeapp.ui.MainMenu


import android.annotation.SuppressLint
import android.util.Log
import androidx.collection.ArrayMap
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.data.Models.Rates
import com.example.exchangeapp.ui.MainActivity
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import com.example.exchangeapp.ui.screens.Screens
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(currencyViewModel: CurrencyViewModel, navController: NavController, activity: MainActivity){
    var dropDownMenuCurrenciesState = mutableStateOf(false)
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
        "USD",
    )
    Scaffold(topBar = { TopAppBarMainMenu(currencyViewModel = currencyViewModel,navController = navController, activity, dropDownMenuCurrenciesState)}) {
        LazyColumnMainMenu(currenciesList, currencyViewModel, activity)
    }
    DropDownMenuCurrencies(currencyViewModel = currencyViewModel, dropDownMenuCurrenciesState, currenciesList)
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
fun DropDownMenuCurrencies(currencyViewModel: CurrencyViewModel, dropDownMenuState: MutableState<Boolean>, currenciesList : List<String>){
    DropdownMenu(expanded = dropDownMenuState.value, onDismissRequest = { dropDownMenuState.value = false }) {
        currenciesList.forEach { label ->
            DropdownMenuItem(onClick = {
                currencyViewModel.getCurrencies(label)
                dropDownMenuState.value = false
            }, text = { Text(text = label)})
        }
    }
}


@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun LazyColumnMainMenu(list : List<String>, currencyViewModel: CurrencyViewModel, activity: MainActivity){
    var currenciesList = emptyList<Any>()
    var map = emptyMap<String, Any>()

    currencyViewModel.currencyResponse.observe(activity, Observer {response ->
        if (response.isSuccessful){
            currenciesList = response.body()!!.rates.getCurrenciesAsList()
            map = list.zip(currenciesList).toMap()
        }
    })
    
    LazyColumn(contentPadding = PaddingValues(vertical = 80.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){

        items(map.toList()){
            Box(modifier = Modifier
                .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .fillParentMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = it.first)
                    Spacer(modifier = Modifier.weight(1f, true))
                    Text(text = it.second.toString())
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "addToFav", modifier = Modifier.clickable {
                        GlobalScope.launch(Dispatchers.IO) {
                            currencyViewModel.addCurrency(CurrencyModel(0, it.first, it.second.toString()))
                        }
                    })
                }
            }
        }

    }
}