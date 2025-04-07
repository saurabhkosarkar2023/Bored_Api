package com.example.board_api.auth.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.board_api.CommonTextField
import com.example.board_api.Navigation.Screens
import com.example.board_api.R

@Composable
fun Login(
    navController: NavController,
    viewModel: AuthViewModel= hiltViewModel()
) {  // Ensures it's created after FirebaseApp
    val loginState = viewModel.loginState.collectAsState()
    var loading by remember { mutableStateOf(false) }
    val loadingState=viewModel.loadingState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context= LocalContext.current
    LaunchedEffect(loginState.value) {
        Log.d("Launcheffect","Am I coming inside launch effect ?")
        if (loginState.value?.isFailure == true){
            Toast.makeText(context,"Wrong Credentials❌",Toast.LENGTH_SHORT).show()
        }
        if(loginState.value?.isSuccess == true){
            navController.navigate(Screens.Home.route){
                popUpTo(0)
                launchSingleTop=true
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = SnackbarHostState()) },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(0.35f)
            ) {
                Image(
                    painter = painterResource(R.drawable.bored_api),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 24.dp)
                        .align(Alignment.Center)
                )
            }
            Text(
                text = "Login",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Enter your email",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            CommonTextField(
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                },
                value = email,
                label = "xyz@gmail.com",
                onValueChange = { email = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enter your password",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            CommonTextField(
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                value = password.toString(),
                label = "xxxxxxxx",
                onValueChange = { password = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(
                onClick = {
                    Log.d("saurabh-login","Email >> $email and password >> $password")
                   viewModel.login(email, password)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = Color.Blue,
                    disabledContentColor = Color.White
                )

            ) {
                when (loadingState.value) {
                     true -> {
                         Box(
                             modifier = Modifier.size(24.dp,24.dp)
                         ){
                             CircularProgressIndicator(
                                 color = Color.White
                             )
                         }
                     }
                    false -> {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W600,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "By clicking in, I accept the terms of services & Privacy policy.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center, maxLines = 2,
            )
        }
    }
}
