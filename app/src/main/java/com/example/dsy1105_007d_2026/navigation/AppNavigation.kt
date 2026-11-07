package com.example.dsy1105_007d_2026.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dsy1105_007d_2026.ui.screen.RegistroScreen
import com.example.dsy1105_007d_2026.ui.screen.ResumenScreen
import com.example.dsy1105_007d_2026.view.DrawerMenu
import com.example.dsy1105_007d_2026.view.ProductoFormScreen
import com.example.dsy1105_007d_2026.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //  Aquí creamos el ViewModel una sola vez
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "registro"
    ) {
        composable("registro") {
            RegistroScreen(navController, usuarioViewModel)
        }

        composable("resumen") {
            ResumenScreen(usuarioViewModel)
        }

        composable("menu") {
            DrawerMenu(username="Prueba", navController)
        }

        composable("producto/{nombre}/{precio}") { backStackEntry ->

            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val precio = backStackEntry.arguments?.getString("precio") ?: ""

            ProductoFormScreen(
                navController = navController,
                nombre = nombre,
                precio = precio
            )
        }

    }
}