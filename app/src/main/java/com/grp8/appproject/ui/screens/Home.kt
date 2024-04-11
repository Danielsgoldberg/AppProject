package com.grp8.appproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.annotations.concurrent.Background
import com.grp8.appproject.R
import com.grp8.appproject.ui.components.BottomBar
import kotlinx.coroutines.launch

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
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
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 40.sp, // Adjust the font size as needed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Add padding for better visibility
                    .background(Color.Transparent)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center // Center the text horizontally
            )
            // Add BottomBar here
            BottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                navigate = { /* Handle navigation */ }
            )
        }
    }
}