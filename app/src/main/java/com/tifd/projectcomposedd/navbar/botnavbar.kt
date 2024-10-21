package com.tifd.projectcomposedd.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tifd.projectcomposedd.screens.GithubProfileScreen
import com.tifd.projectcomposedd.screens.ListScreen
import com.tifd.projectcomposedd.screens.TugasScreen

@Composable
fun botnavbar(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        // Route untuk ListScreen
        composable(Screen.ListScreen.route) {
            ListScreen(navController)
        }

        // Route untuk TugasScreen
        composable(Screen.TugasScreen.route) {
            TugasScreen(navController)
        }

        // Route untuk GithubProfileScreen
        composable(Screen.GithubProfileScreen.route) {
            GithubProfileScreen(
                navController = navController,
                username = "dhanareyhan",)
         }
    }
}