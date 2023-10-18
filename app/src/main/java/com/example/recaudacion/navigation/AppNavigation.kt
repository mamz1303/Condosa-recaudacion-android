package com.example.recaudacion.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recaudacion.ui.screens.MainMenuViewModel
import com.example.recaudacion.ui.screens.LoginPageScreen
import com.example.recaudacion.ui.screens.MainMenuPageScreen
import com.example.recaudacion.ui.screens.RegisterCollection1PageScreen
import com.example.recaudacion.ui.screens.RegisterViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginPageScreen.route) {
        composable(route = AppScreens.LoginPageScreen.route) {
            LoginPageScreen(navController)
        }
        composable(route = AppScreens.MainMenuScreen.route){
            val collectionsViewModel: MainMenuViewModel = viewModel()

            MainMenuPageScreen(collectionsViewModel, navController = navController)
        }
        composable(route = AppScreens.RegisterCollectionScreen.route){
            val collectionsViewModel: RegisterViewModel = viewModel()
            RegisterCollection1PageScreen(collectionsViewModel ,navController)
        }
    }
}