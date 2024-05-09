package com.grp8.appproject.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grp8.appproject.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.height



@Composable
fun Search(find:(searchIngredient: String?) -> Unit, findIngredients:() -> Unit, searchParameter: String?) {
    val alcohol = remember { mutableStateOf(
        if (searchParameter!=null)
        {
            searchParameter.toString()
        }  else {
            ""
        }
    ) }
    val searchIngredient = searchParameter
    val mixer = remember { mutableStateOf("") }
    val additionalIngredient = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()){
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.ingredients),
                contentDescription = "SearchHomePage",
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {


                    Text(
                        text = "Search",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
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
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    TextField(
                        value = alcohol.value,
                        onValueChange = { newText -> alcohol.value = newText },
                        placeholder = { Text("Enter ingredient")},
                        shape = RoundedCornerShape(16.dp)
                    )
                }
                if (additionalIngredient.value) {
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()){
                        TextField(
                            value = mixer.value,
                            onValueChange = { newText -> mixer.value = newText},
                            placeholder = { Text("Enter another ingredient")},
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = "Add ingredient:",
                            modifier = Modifier.padding(4.dp, vertical = 13.dp),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        IconButton(onClick = { additionalIngredient.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add ingredient",
                                tint = Color.Black,
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                    }
                }
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()){
                    Button(
                        onClick = {
                            scope.launch {
                                find(searchParameter)

                                if (searchParameter != null) {
                                    Log.v("SearchParameter", searchParameter)
                                } else
                                {
                                    Log.v("Search Parameter", "Is null")
                                }
                                if (searchIngredient != null) {
                                    Log.v("search ingredietn", searchIngredient)
                                }
                                else
                                {
                                    Log.v("Search Ingredient", "Is null")
                                }


                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.padding(8.dp)
                            .height(45.dp),
                        border = BorderStroke(width=0.5.dp, color = Color.Black),
                    ) {
                        Text(text = "Search", color = Color.Black,
                            style = TextStyle(
                            fontSize = 16.sp))
                    }
                }
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            scope.launch {
                                findIngredients()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.padding(8.dp)
                            .height(50.dp),
                        border = BorderStroke(width=0.5.dp, color = Color.Black)
                    ) {
                        Text(text = "Find ingredients", color = Color.Black,
                            style = TextStyle(
                                fontSize = 16.sp))
                    }
                }
            }
        }
    }
}







