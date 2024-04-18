package com.grp8.appproject.integrations.firestore.authentication


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.launch

@Composable
fun Signup(Ok:() -> Unit, Cancel:() -> Unit){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Button(onClick = {
            scope.launch {
                Ok()
            }
        }
            ) {
            Text(text = "Sign up")
        }

        //Lav en error her også
    }
}