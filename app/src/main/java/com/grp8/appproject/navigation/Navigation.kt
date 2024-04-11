package com.grp8.appproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = "Login") {
        composable("Login") {
            NavController(title = "Login", navigationText = "To Home") {
                controller.navigate("Home"){
                    restoreState=true
                }
            }
        }
        composable("Home") {
            NavController(
                title = "Home",
                navigationText = "To Search",
                backHandler = true,
                back = { controller.popBackStack("Login",inclusive = false,saveState = true) }) {
                controller.navigate("Home")
            }
        }
        composable("Profile") {
            NavController(
                title = "Profile",
                navigationText = "To Search",
                backHandler = true,
                back = { controller.popBackStack("Login",inclusive = false,saveState = true) }) {
                controller.navigate("Home")
            }
        }

        composable("Search") {
            NavController(
                title = "Search",
                navigationText = "<- Back",
                backHandler = true,
                back = { controller.popBackStack("Home",inclusive = false, saveState = true) }) {
                controller.popBackStack("Login", inclusive = false)
            }
        }

    }
}