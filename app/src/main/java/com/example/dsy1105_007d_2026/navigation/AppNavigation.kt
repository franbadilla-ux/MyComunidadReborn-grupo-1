package com.example.dsy1105_007d_2026.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dsy1105_007d_2026.ui.screen.RegistroScreen
import com.example.dsy1105_007d_2026.ui.screen.ResumenScreen
import com.example.dsy1105_007d_2026.view.DrawerMenu
import com.example.dsy1105_007d_2026.view.OportunidadFormScreen
import com.example.dsy1105_007d_2026.viewmodel.UsuarioViewModel
import com.example.dsy1105_007d_2026.view.ListaOportunidadesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //  Aquí creamos el ViewModel una sola vez
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        //startDestination = "registro"
        //startDestination = "oportunidad"
        startDestination = "resumen"
    ) {
        composable("registro") {
            RegistroScreen(navController, usuarioViewModel)
        }

        composable("resumen") {
            ResumenScreen(usuarioViewModel)
        }


        composable("menu") {
            DrawerMenu(
                username = "Usuario de prueba",
                navController = navController
            )
        }

        composable("oportunidad") {
            OportunidadFormScreen(
                navController = navController
            )
        }

    }
}