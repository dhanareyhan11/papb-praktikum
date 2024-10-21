package com.tifd.projectcomposedd.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.tifd.projectcomposedd.BackgroundGradient
import com.tifd.projectcomposedd.DataListView
import com.tifd.projectcomposedd.DataModel
import com.tifd.projectcomposedd.ErrorView
import com.tifd.projectcomposedd.LoadingView
import com.tifd.projectcomposedd.loadDataFromFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var dataList by remember { mutableStateOf(listOf<DataModel>()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    // Fetch data from Firestore
    LaunchedEffect(Unit) {
        loadDataFromFirestore(db, { data ->
            dataList = data
            isLoading = false
        }, {
            isLoading = false
            isError = true
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Failed to load data.")
            }
        })
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = { Text("Data Jadwal Kuliah") },

            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Latar belakang
            BackgroundGradient()

            if (isLoading) {
                LoadingView()
            } else if (isError) {
                ErrorView()
            } else {
                DataListView(dataList)
            }
        }
    }
}

