package com.grp8.appproject.controllers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun NavController(
    title: String,
    navigationText: String,
    backHandler: Boolean = false,
    back: () -> Unit = {},
    navigate: () -> Unit
) {
    if (backHandler) {
        BackHandler {
            back()
        }
    }
    val count = rememberSaveable { mutableIntStateOf(0) }

    Column {
        Text(title)
        Button(onClick = navigate) {
            Text(navigationText)
        }
    }
}