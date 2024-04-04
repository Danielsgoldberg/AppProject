package com.grp8.appproject.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.grp8.appproject.integrations.firestore.model.CocktailFS

@Composable
fun Cocktail(cocktail: CocktailFS)
{
    Column {
        Text(cocktail.id)
        Text(cocktail.name?: "")
    }
}