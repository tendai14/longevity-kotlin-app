package com.example.androidgithubapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidgithubapi.ui.theme.views.mainView.MainScreen
import com.example.androidgithubapi.ui.theme.views.userDetailView.UserDetailsScreen

@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.MainScreen){
            MainScreen(navController = navController)
        }
        composable(
            "repos/{login}"
        )
            { backStackEntry ->
                UserDetailsScreen(navController = navController, argument = backStackEntry.arguments?.getString("login"))
            }

        }
    }
