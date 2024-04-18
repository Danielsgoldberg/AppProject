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
import com.grp8.appproject.ui.components.ScreenScaffold
import com.grp8.appproject.ui.screens.Home
import com.grp8.appproject.ui.screens.Profile
import com.grp8.appproject.ui.screens.Search

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = "Login") {
        composable("Login") {
            Login(
                service = BasicAuthClient(),
                Ok = { controller.navigate("Home") },
                Signup = { controller.navigate("Signup") })
        }

        composable("Signup") {
            Signup(
                service = BasicAuthClient(),
                Ok = { controller.navigate("Home") },
                Cancel = { controller.navigate("Login") })
        }

        composable("Home") {
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Home()
            }
        }

        composable("Search") {
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Search()
            }
        }

        composable("Profile") {
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Profile(
                    service = BasicAuthClient(),
                    Cancel = { controller.navigate("Login") })
            }
        }

    }
}
