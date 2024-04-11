package com.grp8.appproject.navigation

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import com.grp8.appproject.integrations.firestore.authentication.Login
import com.grp8.appproject.integrations.firestore.authentication.Signup
import com.grp8.appproject.ui.screens.Home

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = "Login") {
        composable("Login") {
            Login(service= BasicAuthClient(), Ok = { controller.navigate("Home")}, Signup = {controller.navigate("Signup")})
        }

        composable("Signup") {
            Signup(Ok = {controller.navigate("Home")}, Cancel = {controller.navigate("Login")})
        }

        composable("Home") {
            Home(Cancel = { controller.navigate("Login")})
        }

    }
}
