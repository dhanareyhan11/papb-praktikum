package com.tifd.projectcomposedd.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.tifd.projectcomposedd.data.GithubUser
import com.tifd.projectcomposedd.data.RetrofitInstance
import com.tifd.projectcomposedd.navbar.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GithubProfileScreen(
    username : String,
    navController: NavController) {
    var githubUser by remember { mutableStateOf<GithubUser?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background( brush = Brush.verticalGradient(
                colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))))// Warna biru
            .padding(16.dp)
    )
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    RetrofitInstance.api.getUser("dhanareyhan") // Ganti dengan username yang sesuai
                } catch (e: Exception) {
                    null
                }
            }
            if (response != null) {
                githubUser = response
            } else {
                errorMessage = "Failed to load profile"
            }
            isLoading = false
        }
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    } else if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    } else {
        githubUser?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = it.avatar_Url
                    ),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                    contentScale = ContentScale.Crop
                )


                Text(text = "Username: ${it.login}")
                Text(text = "Name: ${it.name ?: "N/A"}")
                Text(text = "Followers: ${it.followers}")
                Text(text = "Following: ${it.following}")
            }
        }
    }
}
