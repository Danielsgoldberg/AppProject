package com.grp8.appproject.integrations.firestore.authentication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.R
import com.grp8.appproject.models.BasicUser
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember as remember1

@Composable
fun Login(service: BasicAuthClient, Ok: (user:BasicUser) -> Unit, Signup: () -> Unit) {
    val email = remember1 { mutableStateOf("1234@hotmail.com") }
    val password = remember1 { mutableStateOf("567890") }
    val scope = rememberCoroutineScope()
    val errorMessage = remember1 { mutableStateOf<String?>(null) }
    Image(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        painter = painterResource(id = R.drawable.userlogin),
        contentDescription = "CocktailHomePage",
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "User Login",
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 30.dp),
                fontFamily = FontFamily.Serif
            )
            Row() {
                TextField(
                    value = email.value,
                    onValueChange = { newText -> email.value = newText },
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text("Email") },
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .background(Color.Transparent)
                )
            }
            Row() {
                TextField(
                    value = password.value,
                    onValueChange = { newText -> password.value = newText },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text(text = "Password") },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .background(Color.Transparent)
                )
            }
            errorMessage.value?.let { message ->
                Text(
                    message,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        try {

                            if (email.value.isBlank() && password.value.isBlank()) {
                                throw LoginException("Email and password cannot be empty.")

                            } else if (email.value.isBlank()) {
                                throw LoginException("Email cannot be empty.")
                            } else if (password.value.isBlank()) {
                                throw LoginException("Password cannot be empty.")
                            }

                            val signInResult = service.signIn(email.value, password.value)
                            Log.v("Login", "Test User: $signInResult")
                            if (signInResult.error == null) {
                                Ok(signInResult.data!!)
                            } else {
                                throw LoginException("Invalid email or password.")
                            }
                        } catch (e: LoginException) {
                            errorMessage.value = e.message
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Login", color = Color.Black)
            }
            Text(text = "Create user:", color = Color.Black, fontWeight = FontWeight.ExtraBold)
            Button(
                onClick = {
                    scope.launch {
                        Signup()
                    }
                }, colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Signup", color = Color.Black)
            }
        }
    }

}