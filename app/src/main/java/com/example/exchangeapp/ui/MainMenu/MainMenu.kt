package com.example.exchangeapp.ui.MainMenu


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
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
import kotlinx.coroutines.*
import okhttp3.internal.notify
import okhttp3.internal.wait
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
    Scaffold(topBar = { TopAppBarMainMenu(navController = navController,  dropDownMenuCurrenciesState, activity)}) {
        LazyColumnMainMenu(currenciesList, currencyViewModel, activity)

    }
    DropDownMenuCurrencies(currencyViewModel = currencyViewModel, dropDownMenuCurrenciesState, currenciesList, activity)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun TopAppBarMainMenu(navController: NavController, dropDownMenuState : MutableState<Boolean>, activity: MainActivity){
    val currency = activity.getSharedPreferences("CurrencyPrefs", Context.MODE_PRIVATE).getString("Currency", "Выберите валюту")

    SmallTopAppBar(
        title = { Text(text = currency.toString(), modifier = Modifier.clickable {
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
fun DropDownMenuCurrencies(currencyViewModel: CurrencyViewModel, dropDownMenuState: MutableState<Boolean>, currenciesList : List<String>, activity: MainActivity){
    val currency = activity.getSharedPreferences("CurrencyPrefs", Context.MODE_PRIVATE)
    val currencyEditor = currency.edit()


    DropdownMenu(expanded = dropDownMenuState.value, onDismissRequest = { dropDownMenuState.value = false }) {
        currenciesList.forEach { label ->
            DropdownMenuItem(onClick = {
                currencyViewModel.getCurrencies(label)
                currencyEditor.putString("Currency", label).apply()
                dropDownMenuState.value = false
                GlobalScope.launch(Dispatchers.Main){
                    delay(1000)
                    activity.recreate()
                }
            }, text = { Text(text = label)})
        }
    }
}


@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun LazyColumnMainMenu(list : List<String>, currencyViewModel: CurrencyViewModel, activity: MainActivity){
    var currenciesList = emptyList<Double>()
    var map = emptyMap<String, Double>()

    //combine 2 lists to a map
    currencyViewModel.currencyResponse.observe(activity, Observer {response ->

        if (response.isSuccessful){
            currenciesList = response.body()!!.rates.getCurrenciesAsList()
            map = list.zip(currenciesList).toMap()
        }
    })
    //getting sort from settings
    val sharedPefs = activity.getSharedPreferences("Sort", Context.MODE_PRIVATE)
    val mapToList = map.toList()
    val sortedList : List<Pair<String, Double>> =
        when(sharedPefs.getString("SortSelected", "По алфавиту(с начала)").toString()){
            "По алфавиту(с начала)" -> mapToList
            "По алфавиту(с конца)" -> mapToList.asReversed()
            "По возрастанию" -> mapToList.sortedBy { it.second }
            "По убыванию" -> mapToList.sortedBy { it.second }.asReversed()
            else -> {mapToList}
        }
    LazyColumn(contentPadding = PaddingValues(vertical = 80.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){

        items(sortedList){
            Box(modifier = Modifier
                .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .fillParentMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "  " + it.first, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.weight(1f, true))
                    Text(text = "Курс: " + it.second.toString() + "   ", style = MaterialTheme.typography.titleLarge)
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "addToFav", modifier = Modifier
                        .clickable {
                            GlobalScope.launch(Dispatchers.IO) {
                                val getCurrency =
                                    activity.getSharedPreferences("CurrencyPrefs", MODE_PRIVATE)
                                currencyViewModel.addCurrency(
                                    CurrencyModel(
                                        0,
                                        getCurrency
                                            .getString("Currency", "USD")
                                            .toString(),
                                        it.first,
                                        it.second.toString()
                                    )
                                )
                            }
                            Toast
                                .makeText(activity, "Добавлено в избранное", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .size(40.dp))
                }
            }
        }

    }
}