package com.grp8.appproject.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grp8.appproject.ui.screens.Home
import com.grp8.appproject.ui.theme.AppProjectTheme
import java.time.format.TextStyle

@Composable
fun BottomBar(modifier: Modifier = Modifier, navigate:(screen: String) -> Unit) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(56.dp) // Adjust the height as needed
                .background(Color.Transparent)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround // Distribute icons evenly
    ) {
        IconButton(onClick = {navigate("Search")}) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }
        IconButton(onClick = {navigate("Home")} ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.White
            )
        }
        IconButton(onClick = {navigate("Profile")} ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                
                tint = Color.White
            )

        }
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    AppProjectTheme(darkTheme = true) {
        Row(modifier = Modifier.height(40.dp)) {
            BottomBar(modifier = Modifier.background(Color.Gray)) {
                Log.v("App bar Preview", "Clicked")

            }

        }
    }
}