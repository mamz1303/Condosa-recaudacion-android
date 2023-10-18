package com.example.recaudacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.recaudacion.navigation.AppNavigation
import com.example.recaudacion.ui.theme.RecaudacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecaudacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    //color = Color(0xFF000080)
                ) {
                    Text(text = "")
                    //val collectionsViewModel: CollectionsViewModel = viewModel()
                    //MainMenuPageScreen(collectionsUiState = collectionsViewModel.collectionsUiState)
                    AppNavigation()
                }

            }
        }
    }
}
