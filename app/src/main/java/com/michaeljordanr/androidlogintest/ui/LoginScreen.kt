package com.michaeljordanr.androidlogintest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaeljordanr.androidlogintest.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val isLogged by viewModel.isLogged.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if (isLogged.not()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Login")
                    TextField(value = login, onValueChange = { login = it })
                    Text("Password")
                    TextField(value = pass, onValueChange = { pass = it })
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {
                        coroutineScope.launch {
                            viewModel.login(login, pass)
                        }

                    }
                    ) {
                        Text(text = "Login")
                    }
                }
            } else {
                Column {
                    Text(text = "Welcome")
                }
            }
        }
    }
}
