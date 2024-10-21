package com.tifd.projectcomposedd

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.tifd.projectcomposedd.bottomnavigationbar
import com.tifd.projectcomposedd.navbar.botnavbar
import com.tifd.projectcomposedd.ui.theme.ProjectComposeDDTheme

class MainScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectComposeDDTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { bottomnavigationbar(navController = navController) }
    ) {
        botnavbar(navController = navController)
        }
}