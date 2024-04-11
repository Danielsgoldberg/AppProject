package com.grp8.appproject.integrations.firestore.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.grp8.appproject.controllers.NavController

@Composable
fun Login(service:BasicAuthClient, nav:() -> NavController){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column() {
        Row(){
            Text("Email: ")
            TextField(value = email.value, onValueChange = { newText -> email.value = newText})
        }
        Row() {
            Text(text = "Password: ")
            TextField(
                value = password.value,
                onValueChange = {newText -> password.value = newText},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Button(onClick = {
            scope.launch {
                val user = service.signIn(email.value, password.value)
                nav()
            }
        }) {
            Text(text = "Login")
        }
    }
}