package com.grp8.appproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import com.grp8.appproject.integrations.firestore.authentication.Login
import com.grp8.appproject.integrations.firestore.authentication.Signup
import com.grp8.appproject.ui.components.ScreenScaffold
import com.grp8.appproject.ui.components.api.CocktailComponent
import com.grp8.appproject.ui.screens.Favorites
import com.grp8.appproject.ui.screens.Home
import com.grp8.appproject.ui.screens.Profile
import com.grp8.appproject.ui.screens.Search
import com.grp8.appproject.ui.screens.SearchResults

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = "Login") {
        composable("Login") {
            Login(
                service = BasicAuthClient(),
                Ok = { name:String -> controller.navigate("Home?username="+name) },
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

        composable(route = "Search?drinksName={drinksName}",
                   arguments = listOf(
                       navArgument("drinksName")
                        { defaultValue = "" })
        ) { backStackEntry ->
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Search(searchParameter = backStackEntry.arguments?.getString("drinksName") ?: "" ,
                       find = {controller.navigate("SearchResults")},
                       findIngredients = {
                    controller.navigate("IngredientList")
                })
            }
        }

        composable(route = "Profile?username={username}",
            arguments = listOf(
                navArgument("username")
                { defaultValue = "" }) )
        { backStackEntry ->
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Profile(
                    service = BasicAuthClient(),
                    cancel = { controller.navigate("Login") },
                    findfavorites = {controller.navigate("Favorites")},
                    username = backStackEntry.arguments?.getString("username") ?: "" )
            }
        }

         composable("SearchResults"){
             SearchResults(
                 cancel = { controller.navigate("Search") })
         }

        composable("Favorites"){
            Favorites (
                cancel = {controller.navigate("Profile")})
        }

        composable("IngredientList")
        {
            CocktailComponent(backToSearch = {name: String -> controller.navigate("Search?drinksName="+name)})
        }

    }
}
