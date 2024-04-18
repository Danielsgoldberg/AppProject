package com.grp8.appproject.ui.screens
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.grp8.appproject.R

@Composable
fun Profile() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp) // Set the size of the circular image
                .clip(CircleShape) // Clip the box with a circular shape
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.background_bar),
                contentDescription = "ProfilePicture",
                contentScale = ContentScale.Crop
            )
            
            Text(text = "Username here based on login")
        }
    }


}


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Profile",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 48.sp,

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center // Center the text horizontally

        )
    }
}



