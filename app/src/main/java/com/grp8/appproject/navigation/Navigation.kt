package com.grp8.appproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.grp8.appproject.integrations.firestore.CocktailServices
import com.grp8.appproject.integrations.firestore.authentication.BasicAuthClient
import com.grp8.appproject.integrations.firestore.authentication.Login
import com.grp8.appproject.integrations.firestore.authentication.Signup
import com.grp8.appproject.ui.components.ScreenScaffold
import com.grp8.appproject.ui.components.api.DrinksComponent
import com.grp8.appproject.ui.screens.CocktailsList

import com.grp8.appproject.ui.screens.Home
import com.grp8.appproject.ui.screens.Profile
import com.grp8.appproject.ui.screens.CocktailsList
import com.grp8.appproject.ui.screens.NewCocktailsList
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
                       find = {searchSpirit: String?, searchMixer: String? -> controller.navigate("newCocktailsList?searchSpirit=$searchSpirit&searchMixer=$searchMixer")},
                       findIngredients = {
                    controller.navigate("IngredientList")
                })
            }
        }

        composable(route = "Profile")
        {
            ScreenScaffold(
                Search = { controller.navigate("Search") },
                Home = { controller.navigate("Home") },
                Profile = { controller.navigate("Profile") }) {
                Profile(
                    service = BasicAuthClient(),
                    cancel = { controller.navigate("Login") },
                    findfavorites = {controller.navigate("Favorites")})
            }
        }

//        composable(route = "SearchResults?favorite={favorite}",
//            arguments = listOf(
//                navArgument("favorite")
//                { defaultValue = "" })
//        ) { backStackEntry ->
//            CocktailsList(
//                goBack = { controller.navigate("Search") },
//                favoriteParameter = backStackEntry.arguments?.getString("favorite") ?: "" )
//        }

        composable("NewCocktailsList?searchSpirit={searchSpirit}&searchMixer={searchMixer}",
            arguments = listOf(
                navArgument("searchSpirit")
                { defaultValue = "" },
                navArgument("searchMixer")
                { defaultValue = "" })
        )   { backStackEntry ->
            NewCocktailsList(cocktailServices = CocktailServices(),
                goBack = { controller.navigate("Search")},
                    searchSpirit = backStackEntry.arguments?.getString("searchSpirit") ?: "" ,
                    searchMixer = backStackEntry.arguments?.getString("searchMixer") ?:"" ) }


        composable("IngredientList")
        {
            DrinksComponent(backToSearch = {name: String -> controller.navigate("Search?drinksName=$name")})
        }

    }
}
