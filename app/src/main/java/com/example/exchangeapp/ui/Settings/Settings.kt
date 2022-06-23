package com.example.exchangeapp.ui.Settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.exchangeapp.ui.MainMenu.TopAppBarMainMenu
import com.example.exchangeapp.ui.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController){
    Scaffold(topBar = { TopAppBarSettings(navController = navController) }) {
        Column() {
            Text(text = "Settings")
        }
    }
}

@Composable
fun TopAppBarSettings(navController: NavController){
    SmallTopAppBar(
        title = { Text(text = "Настройки")},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "toMainWindow", modifier = Modifier.clickable {
                navController.navigate(Screens.MainMenu.route)
            })
        }
    )
}