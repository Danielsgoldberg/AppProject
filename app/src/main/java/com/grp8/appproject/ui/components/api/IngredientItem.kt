package com.grp8.appproject.ui.components.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun IngredientItem(drinks: Drink)
{
    Column {
        Text("I want: ")
        Row {
            Button(onClick = {

            }) {
                Text(text = drinks.strIngredient1.toString())
            }

        }
    }
}