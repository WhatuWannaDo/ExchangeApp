package com.example.exchangeapp.ui.screens

sealed class Screens(val route : String){
    object MainMenu : Screens(route = "main_menu_route")
    object FavoriteCurrencies : Screens(route = "favorite_route")
    object Settings : Screens(route = "settings_route")
}
