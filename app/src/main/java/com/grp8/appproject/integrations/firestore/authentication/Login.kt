package com.grp8.appproject.integrations.firestore.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun Login(service:BasicAuthClient, Ok:() -> Unit, Signup:()->Unit){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val errorMessage = remember { mutableStateOf<String?>(null) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Row()
            {
                Text(
                    "User Login",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(){
                Text("Email: ")
                TextField(
                    value = email.value,
                    onValueChange = {newText -> email.value = newText},
                    shape = RoundedCornerShape(16.dp)
                )
            }
            Row() {
                Text(text = "Password: ")
                TextField(
                    value = password.value,
                    onValueChange = {newText -> password.value = newText},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(16.dp)
                )
            }
            errorMessage.value?.let { message ->
                Text(
                    message,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            //Lav en error her også
            Button(onClick = {
                scope.launch {
                    //val user = service.signIn(email.value, password.value)
                    try {
                        if (email.value.isBlank() || password.value.isBlank()) {
                            throw LoginException("Email and password cannot be empty.")
                        }

                        val user = service.signIn(email.value, password.value)
                        if (user != null) {
                            Ok()
                        } else {
                            throw LoginException("Invalid email or password.")
                        }
                    } catch (e: LoginException) {
                        errorMessage.value = e.message
                    }
                    //Ok()
                }
            }) {
                Text(text = "Login")
            }
            Button(onClick = {
                scope.launch {
                    Signup()
                }
            },
                modifier = Modifier.padding(8.dp)) {
                Text(text = "Signup")
            }
        }
    }

}