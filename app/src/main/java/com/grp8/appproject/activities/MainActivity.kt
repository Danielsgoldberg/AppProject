package com.grp8.appproject.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import com.grp8.appproject.integrations.firestore.authentication.Login
import com.grp8.appproject.navigation.Navigation
import com.grp8.appproject.ui.screens.Cocktails
import com.grp8.appproject.ui.screens.Home
import com.grp8.appproject.ui.screens.Profile
import com.grp8.appproject.ui.theme.AppProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AppProjectTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(controller = navController)
                }
            }
        }
    }
}
