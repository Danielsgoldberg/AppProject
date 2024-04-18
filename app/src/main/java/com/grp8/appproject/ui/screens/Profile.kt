package com.grp8.appproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient


@Composable
fun Profile(service: BasicAuthClient, Cancel:() -> Unit) {
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

        Button(onClick = {
            service.signOut()
            Cancel() }) {
            Text(text = "Sign out")
        }
    }
}



