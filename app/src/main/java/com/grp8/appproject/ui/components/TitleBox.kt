package com.grp8.appproject.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleBox(title: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    text = title,
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
    }
}

