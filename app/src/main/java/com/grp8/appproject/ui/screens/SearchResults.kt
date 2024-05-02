package com.grp8.appproject.ui.screens
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.R
import kotlinx.coroutines.launch

@Composable
fun SearchResults(cancel:() -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val name="Gin tonic"
    val amountSpiritus = "2"
    val Spritus = "Gin"
    val amountSoda="10"
    val nameSoda="Tonic"
    val extra = "1 skive Agurk"
    val instruction = "1. Find et glas og fyld det med isterninger.\n 2. Hæld først Gin i glasset og derefter tonic. \n 3. Rør rundt og pynt med en skive agurk"

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()){
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
                Box(modifier = Modifier
                    .padding(10.dp)
                    .border(width = 0.5.dp, color = Color.Black)
                    .background(color = Color.White)) {
                    Column{
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = " ${name}",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = { isFavorite = !isFavorite },
                                modifier = Modifier.padding(start = 4.dp)
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorites",
                                    tint = Color.Black
                                )
                            }
                        }
                        Row{
                            Image(
                                painter = painterResource(id = R.drawable.gintonci),
                                contentDescription = "Drink image",
                                modifier = Modifier.width(130.dp)
                            )
                            Column {
                                Text(
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                    modifier = Modifier.padding(8.dp),
                                    text = "Spiritus: ${amountSpiritus} cl ${Spritus}"
                                )
                                Text(
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                    modifier = Modifier.padding(8.dp),
                                    text = "Sodavand: ${amountSoda} cl ${nameSoda}"
                                )
                                Text(
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                    modifier = Modifier.padding(8.dp),
                                    text = "Tilføj også: ${extra}"
                                )
                                Text(
                                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                                    modifier = Modifier.padding(8.dp),
                                    text = "Instruktion: \n ${instruction} \n"
                                )
                            }
                        }
                    }
                }
            }
            Column {
                IconButton(onClick = {
                    scope.launch {
                        cancel()
                    }}) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}