package com.grp8.appproject.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.R
import com.grp8.appproject.integrations.firestore.CocktailServices
import com.grp8.appproject.integrations.firestore.model.Cocktail
import kotlinx.coroutines.launch
import loadImageFromUri


@Composable
fun NewCocktailsList(
    cocktailServices: CocktailServices,
    goBack: () -> Unit,
    searchSpirit: String,
    searchMixer: String
) {

    val cocktailsState = remember { mutableStateOf<List<Cocktail>>(emptyList()) }
    val scope = rememberCoroutineScope()
    var filteredCocktails = listOf<Cocktail>()


    LaunchedEffect(key1 = Unit) {
        val cocktails = cocktailServices.getCocktails().map { it.toCocktail() }
        cocktailsState.value = cocktails
        Log.v("Cocktails initial state", cocktails.count().toString())
    }

    if (searchSpirit != "" && searchMixer == "") {

        filteredCocktails = remember(cocktailsState.value) {
            cocktailsState.value.filter { it.spirit == searchSpirit }

        }

    } else if (searchMixer != "" && searchSpirit == "") {
        filteredCocktails = remember(cocktailsState.value) {
            cocktailsState.value.filter { it.mixer == searchMixer }

        }
    } else if (searchMixer != "" && searchSpirit != "") {
        filteredCocktails = remember(cocktailsState.value) {
            cocktailsState.value.filter { it.mixer == searchMixer && it.spirit == searchSpirit }

        }
    } else {
        filteredCocktails = cocktailsState.value
    }
    Log.v("Search Mixer:", searchMixer)
    Log.v("SearchSpirit:", searchSpirit)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            IconButton(onClick = {
                scope.launch {
                    goBack()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.ingredients),
                contentDescription = "SearchHomePage",
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Results",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                    )
                    Divider(
                        color = Color.Black.copy(alpha = 0.35f),
                        thickness = 2.dp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                    )
                }

                DisplayCocktails(filteredCocktails)
                Log.v("Number of cocktails", filteredCocktails.count().toString())

                if (filteredCocktails.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .border(width = 0.5.dp, color = Color.Black)
                            .background(color = Color.White)
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "No cocktail found with your search criteria",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.weight(1f)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayCocktails(cocktails: List<Cocktail>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cocktails.size) { index ->
            val cocktail = cocktails[index]

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .border(width = 0.5.dp, color = Color.Black)
                    .background(color = Color.White)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = " ${cocktail.name}",
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.weight(1f)
                        )

                    }
                    Row {
                        loadImageFromUri(
                            context = LocalContext.current,
                            url = "${cocktail.image}",
                            contentDescription = "Content",
                            modifier = Modifier.width(130.dp),
                            placeholderResId = R.drawable.loading
                        )
                        Log.v("Picture ID", "${cocktail.image}")

                        Column {
                            Text(
                                style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                modifier = Modifier.padding(8.dp),
                                text = "${cocktail.spiritAmount} cl ${cocktail.spirit}"
                            )
                            Text(
                                style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                modifier = Modifier.padding(8.dp),
                                text = "${cocktail.mixerAmount} cl ${cocktail.mixer}"
                            )

                            Text(
                                style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                modifier = Modifier.padding(8.dp),
                                text = "Instruktion: \n ${cocktail.instruction} \n"
                            )
                        }
                    }
                }
            }
        }
    }
}
