package com.example.exchangeapp.ui.Settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.exchangeapp.ui.MainActivity
import com.example.exchangeapp.ui.MainMenu.TopAppBarMainMenu
import com.example.exchangeapp.ui.screens.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController, activity: MainActivity){
    val sharedPefs = activity.getSharedPreferences("Sort", MODE_PRIVATE)
    val prefsEditor = sharedPefs.edit()
    val sort = listOf("По алфавиту(с начала)", "По алфавиту(с конца)", "По возрастанию", "По убыванию")
    val (selectedSearchButtonOptions, onSearchButtonOptionsSelected) = remember { mutableStateOf(sort[getSort(activity)]) }

    Scaffold(topBar = { TopAppBarSettings(navController = navController) }) {
        Column(
            Modifier
                .selectableGroup()
                .padding(vertical = 80.dp, horizontal = 20.dp),
        ) {
            sort.forEach {
                Row() {
                    RadioButton(
                        selected = it == selectedSearchButtonOptions,
                        onClick = {
                            onSearchButtonOptionsSelected(it)
                            prefsEditor.putString("SortSelected", it).apply()
                        })
                    Text(text = it,  modifier = Modifier.clickable {
                        onSearchButtonOptionsSelected(it)
                        prefsEditor.putString("SortSelected", it).apply()
                    })
                }
            }
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
// fun for get radio button statement
fun getSort(activity: MainActivity) : Int {
    val prefsSort : SharedPreferences = activity.getSharedPreferences("Sort", MODE_PRIVATE)
    when(prefsSort.getString("SortSelected", "По алфавиту(с начала)").toString()){
        "По алфавиту(с начала)" -> return 0
        "По алфавиту(с конца)" -> return 1
        "По возрастанию" -> return 2
        "По убыванию" -> return 3
    }
    return 0
}