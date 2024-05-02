package com.grp8.appproject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.grp8.appproject.R


@Composable
fun Profile(findfavorites:() -> Unit, service: BasicAuthClient, cancel:() -> Unit, username: String) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){

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
            Divider(
                color = Color.Black.copy(alpha = 0.35f),
                thickness = 2.dp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.profilepicture),
                contentDescription = "ProfilePicture",
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )
            Text(
                text = "Username: $username",
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontSize = 27.sp,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Button(onClick = {
            service.signOut()
            cancel() }) {
            Text(text = "Signout")
        }
    }

    Column() {
        Button(
            onClick = {
                scope.launch {
                    findfavorites()
                }
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .padding(8.dp)
                .height(45.dp),
            border = BorderStroke(width=0.5.dp, color = Color.Black),
        ) {
            Text(text = "Favorties", color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp)
            )
        }
    }


}




