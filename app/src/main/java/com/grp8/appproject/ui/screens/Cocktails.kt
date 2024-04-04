package com.grp8.appproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.grp8.appproject.integrations.firestore.CocktailServices
import com.grp8.appproject.integrations.firestore.model.CocktailFS
import com.grp8.appproject.ui.components.Cocktail

@Composable
fun Cocktails() {
    val service = CocktailServices()
    var cocktails by remember { mutableStateOf<List<CocktailFS>>(emptyList()) }
    LaunchedEffect(Unit) {
        cocktails = service.getCocktails()
    }

    Column {
        cocktails.map { Cocktail(it) }
    }
}