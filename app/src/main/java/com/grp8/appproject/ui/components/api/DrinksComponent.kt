package com.grp8.appproject.ui.components.api

import KtorClient
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DrinksComponent(backToSearch:(String) -> Unit)
{
    val scope = rememberCoroutineScope()
    val ktorService = KtorClient()
    val data = remember { mutableStateOf(Drinks()) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)

        val drinks = ktorService.get()
        if(drinks != null)
            data.value = drinks
        else
            data.value = Drinks()


        isLoading.value = false
    }
    DisposableEffect(Unit)
    {
        onDispose { ktorService.close() }
    }
    if(isLoading.value)
    {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(0.4f)
            )
        {
            CircularProgressIndicator(color = androidx.compose.ui.graphics.Color.Black, strokeWidth = 5.dp)
        }

    } else {
        Column {
            IconButton(onClick = {
                scope.launch {
                    backToSearch("")
                }}) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            LazyColumn{
                items(data.value.drinks) {
                    Log.v("Ktor Test", it.toString())
                    IngredientItem(it, backToSearch = backToSearch)
                    var drinkName = it.strIngredient1.toString()
                }
            }
        }

    }

}