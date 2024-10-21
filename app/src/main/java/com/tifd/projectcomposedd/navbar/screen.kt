package com.tifd.projectcomposedd.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object ListScreen : Screen("list_screen", "Mata Kuliah", Icons.Default.Home)
    object TugasScreen : Screen("tugas_screen", "Tugas", Icons.Default.Search)
    object GithubProfileScreen : Screen("github_profile", "GitHub", Icons.Default.AccountCircle)
}