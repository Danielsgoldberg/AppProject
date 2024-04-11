package com.grp8.appproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.grp8.appproject.controllers.NavController
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import com.grp8.appproject.integrations.firestore.authentication.Login
import com.grp8.appproject.ui.screens.Cocktails
import com.grp8.appproject.ui.theme.AppProjectTheme
//class LoginActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        val service = BasicAuthClient();
//        val nav = NavController(title="", navigationText = "");
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppProjectTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Login(service, nav)
//                }
//            }
//        }
//    }
//}