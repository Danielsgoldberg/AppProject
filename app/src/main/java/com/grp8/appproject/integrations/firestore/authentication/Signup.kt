package com.grp8.appproject.integrations.firestore.authentication


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Signup(service:BasicAuthClient, Ok:() -> Unit, Cancel:() -> Unit){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val errorMessage = remember { mutableStateOf<String?>(null) }
    Column() {
        IconButton(onClick = {
            scope.launch {
                Cancel()
            }}) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Row(){
            Text("Email: ")
            TextField(value = email.value, onValueChange = { newText -> email.value = newText})
        }
        Row() {
            Text(text = "Password: ")
            TextField(
                value = password.value,
                onValueChange = {newText -> password.value = newText},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible.value) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = passwordVisible.value,
                onCheckedChange = { passwordVisible.value = it },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        errorMessage.value?.let { message ->
            Text(
                message,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Button(onClick = {
            scope.launch {
                try {
                    if (email.value.isBlank() && password.value.isBlank()) {
                        throw LoginException("Email and password cannot be empty.")

                    } else if(email.value.isBlank()){
                        throw LoginException("Email cannot be empty.")
                    } else if(password.value.isBlank()){
                        throw LoginException("Password cannot be empty.")
                    }

                    val user = service.signUp(email.value, password.value)
                    Log.v("Login", "Test User: $user")
                    if (user.error == null) {
                        Ok()
                    } else {
                        throw LoginException("Invalid email or password.")
                    }
                } catch (e: LoginException) {
                    errorMessage.value = e.message
                }
            }
        }
            ) {
            Text(text = "Sign up")
        }
    }
}