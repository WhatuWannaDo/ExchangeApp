package com.example.exchangeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.exchangeapp.ui.Favorites.FavoriteCurrencies
import com.example.exchangeapp.ui.MainMenu.MainMenu
import com.example.exchangeapp.ui.Settings.Settings
import com.example.exchangeapp.ui.ViewModels.CurrencyViewModel
import com.example.exchangeapp.ui.screens.Screens

@Composable
fun SetupNavGraph(navController: NavHostController, currencyViewModel: CurrencyViewModel, activity : MainActivity) {
    NavHost(navController = navController, startDestination = Screens.MainMenu.route, builder = {
        composable(route = Screens.MainMenu.route, content = {
            MainMenu(currencyViewModel = currencyViewModel, navController, activity)
        })
        composable(route = Screens.FavoriteCurrencies.route, content = {
            FavoriteCurrencies(navController = navController, currencyViewModel = currencyViewModel)
        })
        composable(route = Screens.Settings.route, content = {
            Settings(navController = navController)
        })
    })
}