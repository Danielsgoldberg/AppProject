package com.grp8.appproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun Search(find:() -> Unit, findIngredients:() -> Unit, searchParameter: String?) {
    val alcohol = remember { mutableStateOf("") }
    if (searchParameter!=null)
    {
        alcohol.value = searchParameter
    }
    val mixer = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    //val searchResult = remember { mutableStateOf("") }

//    fun find(){
//        searchResult.value = "Searching for alcohol: ${alcohol.value}, mixer: ${mixer.value}"
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Row()
            {
                Text(
                    text = "Search",
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 48.sp,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center // Center the text horizontally
                )
            }
            Row {
                Text(text = "Alcohol:  ")
                TextField(

                    value = alcohol.value,
                    onValueChange = { newText -> alcohol.value = newText},
                    placeholder = { Text("Enter specific alcohol")},
                    shape = RoundedCornerShape(16.dp)
                )
            }
            Row {
                Text(text = "Mixer:  ")
                TextField(
                    value = mixer.value,
                    onValueChange = { newText -> mixer.value = newText},
                    placeholder = { Text("Enter specific mixer")},
                    shape = RoundedCornerShape(16.dp)
                )
            }

            Row()
            {
                Button(onClick = {
                    scope.launch {
                        find()
                    }
                }) {
                    Text(text = "Search")
                }
                Button(onClick =  {
                    scope.launch{
                        findIngredients()
                    }

                }) {
                    Text(text = "Find Ingredients")
                }
            }
        }
    }
}






