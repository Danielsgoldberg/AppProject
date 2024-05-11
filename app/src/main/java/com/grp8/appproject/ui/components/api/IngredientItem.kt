package com.grp8.appproject.ui.components.api

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun IngredientItem(drinks: Drink, backToSearch: (String) -> Unit) {
    val scope = rememberCoroutineScope()
    Column() {
        Row() {
            Button(
                onClick = {
                    scope.launch {
                        backToSearch(drinks.strIngredient1.toString())
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White, contentColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(6.dp)
                    .border(width = 0.5.dp, color = Color.Black)
                    //.background(color = Color.White)
                    .width(500.dp)

            ) {
                Box() {
                    Text(text = drinks.strIngredient1.toString(), color = Color.Black)
                }
            }

        }
    }
}