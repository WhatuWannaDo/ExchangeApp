package com.example.exchangeapp.ui.Favorites

import android.annotation.SuppressLint
import android.app.LauncherActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.exchangeapp.data.Models.CurrencyModel
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import com.example.exchangeapp.ui.screens.Screens
import com.example.exchangeapp.ui.theme.Typography


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteCurrencies(navController: NavController, currencyViewModel: CurrencyViewModel){
    val getAllFavoriteCurrencies = currencyViewModel.getAllCurrencies.collectAsState(initial = listOf()).value
    Scaffold(topBar = { TopAppBarFavoriteCurrencies(navController = navController) }) {
        LazyColumnFavorites(list = getAllFavoriteCurrencies )
    }
}

@Composable
fun TopAppBarFavoriteCurrencies(navController: NavController){
    SmallTopAppBar(
        title = { Text(text = "Сохраненные")},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "toMainWindow", modifier = Modifier.clickable {
                navController.navigate(Screens.MainMenu.route)
            })
        }
    )
}

@Composable
fun LazyColumnFavorites(list: List<CurrencyModel>){

    LazyColumn(contentPadding = PaddingValues(vertical = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(list){
            Box(modifier = Modifier
                .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
                .fillParentMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Валюта: " + it.currency)
                    Text(text = " Курс: " + it.amount.toString())

                }
            }
        }

    }
}