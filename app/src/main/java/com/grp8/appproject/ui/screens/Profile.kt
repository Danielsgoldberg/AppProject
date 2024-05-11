package com.grp8.appproject.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.grp8.appproject.R
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import kotlinx.coroutines.launch


@Composable
fun Profile(findfavorites: () -> Unit, service: BasicAuthClient, cancel: () -> Unit) {
    val scope = rememberCoroutineScope()
    val username = Firebase.auth.currentUser?.email
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth())
                {
                    Text(
                        text = "Profile",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                    Divider(
                        color = Color.Black.copy(alpha = 0.35f),
                        thickness = 2.dp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                    )
                }
            }

            Column(modifier = Modifier.align(Alignment.CenterStart))
            {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.profilepicture),
                        contentDescription = "ProfilePicture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Username: $username",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
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
                        border = BorderStroke(width = 0.5.dp, color = Color.Black),
                    ) {
                        Text(
                            text = "Favorites", color = Color.Black,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        service.signOut()
                        cancel()
                    }) {
                        Text(text = "Sign-out")
                    }
                }
            }
        }
    }
}




