package com.grp8.appproject.ui.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.grp8.appproject.R
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.storage
import com.grp8.appproject.models.BasicUser
import com.grp8.appproject.ui.components.TitleBox
import com.grp8.appproject.ui.components.image.ImageEditor
import com.grp8.appproject.ui.components.image.bitmapFromResource


@Composable
fun Profile(
    user: BasicUser,
    changeProfileImage:(uri:Uri)->Unit,
    signOut: () -> Unit
) {
    var editing by remember { mutableStateOf(false) }

    if (editing) {
        ImageEditor(cancel = { editing = false }, useImage ={
            changeProfileImage(it)
            editing = false
        })
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            TitleBox("Profile")

            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                model = if (user.profileIcon == Uri.EMPTY) {
                    bitmapFromResource(resId = R.drawable.default_picture)
                } else {
                    user.profileIcon
                },
                contentDescription = ""
            )


            Text(
                text = "Username: ${user.displayName}",
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontSize = 22.sp,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                editing = true
            }) {
                Text("Pick a new picture")
            }

            Button(onClick = signOut) {
                Text(text = "Sign-out")
            }
        }

    }
}






