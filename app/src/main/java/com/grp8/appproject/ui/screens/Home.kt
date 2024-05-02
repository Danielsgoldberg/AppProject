package com.grp8.appproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.R
import kotlinx.coroutines.launch

@Composable
fun Home() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {

        Box(modifier = Modifier
            .fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.background_bar),
                contentDescription = "CocktailHomePage",
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Welcome to Virtual Bartender",
                color = Color.White,
                fontFamily = FontFamily.Cursive,
                fontSize = 48.sp, // Adjust the font size as needed

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                textAlign = TextAlign.Center
            )
            Box(
                Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .height(250.dp)
                    .width(300.dp)
            ) {
                Column {
                    Text(text = "Navigate in the menu items to: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(25.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = "Search for drinks with specific ingredients",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Search",
                            tint = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(text = "Find your previous favorite drinks",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

    }
}