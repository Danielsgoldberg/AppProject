package com.grp8.appproject.ui.components.image

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grp8.appproject.R


//  19 9
@Composable
fun ImageEditor(
    cancel: () -> Unit,
    useImage: (url: Uri) -> Unit
) {
    val context = LocalContext.current
    val url = remember {
        mutableStateOf(Uri.EMPTY)
    }


    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { uri: Uri? ->
            if (uri != null) {
                url.value = uri
            } else {
                url.value = Uri.EMPTY
            }
        }



    if (url.value == Uri.EMPTY) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .clickable {
                imagePickerLauncher.launch(arrayOf("image/*"))
            }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Load from gallery", style = MaterialTheme.typography.headlineLarge)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Color.Gray)
                        .aspectRatio(16f / 9f)
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.height(100.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_download_for_offline_24),
                        contentDescription = "Upload Image"
                    )
                }
            }
        }
    } else {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            AsyncImage(

                model = url.value, contentDescription = "TEST",
                modifier = Modifier
                    .height(300.dp)
                    .aspectRatio(16f / 9f)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = {
                        useImage(url.value)
                    }) {
                    Text(
                        "Use Image",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = cancel
                ) {
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}
