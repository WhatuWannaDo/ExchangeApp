package com.example.exchangeapp.ui.MainMenu


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import com.example.exchangeapp.ui.screens.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(currencyViewModel: CurrencyViewModel, navController: NavController){
    Scaffold(topBar = { TopAppBarMainMenu(navController = navController)}) {
        Column() {
            Text(text = "SSSSS")
        }
    }
}

@Composable
fun TopAppBarMainMenu(navController: NavController){
    SmallTopAppBar(
        title = { Text(text = "Выбор валюты", modifier = Modifier.clickable {

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