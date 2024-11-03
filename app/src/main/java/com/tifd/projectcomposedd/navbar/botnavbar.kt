package com.tifd.projectcomposedd.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tifd.projectcomposedd.screens.GithubProfileScreen
import com.tifd.projectcomposedd.screens.ListScreen
import com.tifd.projectcomposedd.screens.TugasScreen
import com.tifd.projectcomposedd.datamodel.TugasViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun botnavbar(navController: NavHostController) {
    val tugasViewModel: TugasViewModel= viewModel()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        // Route untuk ListScreen
        composable(Screen.ListScreen.route) {
            ListScreen(navController)
        }

        // Route untuk TugasScreen
        composable(Screen.TugasScreen.route) {
            TugasScreen(
            navController = navController,
                tugasViewModel = tugasViewModel
            )
        }

        // Route untuk GithubProfileScreen
        composable(Screen.GithubProfileScreen.route) {
            GithubProfileScreen(
                navController = navController,
                username = "dhanareyhan",)
         }
    }
}