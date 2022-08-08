package com.bivizul.sportingeventscalendar2022.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bivizul.sportingeventscalendar2022.beforemain.BeforeMain
import com.bivizul.sportingeventscalendar2022.beforemain.BeforeMainViewModel
import com.bivizul.sportingeventscalendar2022.main.MainScreen
import com.bivizul.sportingeventscalendar2022.other.Calendar
import com.bivizul.sportingeventscalendar2022.settings.SettingsScreen

sealed class Routes(val route: String) {
    object BeforeMain : Routes("beforemain")
    object Main : Routes("main")
    object Calendar : Routes("calendar")
    object Settings : Routes("settings")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.BeforeMain.route,
    beforeMainViewModel: BeforeMainViewModel,
) {

    val activity = LocalContext.current as Activity

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Routes.BeforeMain.route) {
            BeforeMain(
                navController = navController,
                beforeMainViewModel = beforeMainViewModel
            )
            BackHandler {
                activity.finishAndRemoveTask()
                System.exit(0)
            }
        }

        composable(route = Routes.Main.route) {
            MainScreen(
                navController = navController
            )
            BackHandler {
                activity.finishAndRemoveTask()
                System.exit(0)
            }
        }

        composable(route = Routes.Calendar.route) {
            Calendar()
        }

        composable(route = Routes.Settings.route) {
            SettingsScreen()
        }
    }
}