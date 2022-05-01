package com.example.composenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenotes.screens.home.HomeScreen
import com.example.composenotes.screens.utils.ScreenConstants.LOGIN_SCREEN
import com.example.composenotes.screens.utils.ScreenConstants.REGISTER_SCREEN
import com.example.composenotes.screens.utils.ScreenConstants.SPLASH_SCREEN
import com.example.composenotes.screens.login.LoginScreen
import com.example.composenotes.screens.register.RegisterScreen
import com.example.composenotes.screens.splash.SplashScreen
import com.example.composenotes.screens.utils.ScreenConstants.HOME
import com.example.composenotes.ui.theme.ComposeNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationComponent(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(route = SPLASH_SCREEN) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(LOGIN_SCREEN)
            }
        }
        composable(LOGIN_SCREEN) {
            LoginScreen(
                onRegisterClicked = {
                    navController.navigate(REGISTER_SCREEN)
                }, onLoginSuccessful = {
                    navController.popBackStack()
                    navController.navigate(HOME)
                })
        }
        composable(REGISTER_SCREEN) {
            RegisterScreen(navController = navController)
        }
        composable(HOME) {
            HomeScreen()
        }
    }
}