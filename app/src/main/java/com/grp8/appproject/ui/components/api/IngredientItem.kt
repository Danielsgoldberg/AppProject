package com.grp8.appproject.ui.components.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun IngredientItem(drinks: Drink, backToSearch:(String) -> Unit)
{
    val scope = rememberCoroutineScope()
    Column {
        Row {
            Button(onClick = {
                scope.launch {
                    backToSearch(drinks.strIngredient1.toString())
                }
            }) {
                Text(text = drinks.strIngredient1.toString())
            }

        }
    }
}