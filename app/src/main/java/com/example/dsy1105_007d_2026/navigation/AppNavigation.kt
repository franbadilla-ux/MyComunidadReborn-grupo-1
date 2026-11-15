package com.example.dsy1105_007d_2026.navigation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dsy1105_007d_2026.ui.screen.RegistroScreen
import com.example.dsy1105_007d_2026.ui.screen.ResumenScreen
import com.example.dsy1105_007d_2026.ui.theme.HomeScreen
import com.example.dsy1105_007d_2026.utils.CameraPermissionHelper
import com.example.dsy1105_007d_2026.view.DrawerMenu
import com.example.dsy1105_007d_2026.view.ListaOportunidadesScreen
import com.example.dsy1105_007d_2026.view.OportunidadFormScreen
import com.example.dsy1105_007d_2026.view.ProductoFormScreen
import com.example.dsy1105_007d_2026.view.QrScannerScreen
import com.example.dsy1105_007d_2026.viewmodel.QrViewModel
import com.example.dsy1105_007d_2026.viewmodel.UsuarioViewModel
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //  Aquí creamos el ViewModel una sola vez
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Login (HomeScreen): navega a "menu/{usuario}" al iniciar sesión
        composable("login") {
            HomeScreen(navController)
        }
        composable("registro") {
            RegistroScreen(navController, usuarioViewModel)
        }

        composable("resumen") {
            ResumenScreen(usuarioViewModel)
        }

        composable("menu") {
            DrawerMenu(username="Prueba", navController)
        }
        // Menú con el usuario que inició sesión (lo usa HomeScreen)
        composable(
            route = "menu/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { entry ->
            DrawerMenu(
                username = entry.arguments?.getString("username").orEmpty(),
                navController = navController
            )
        }
        composable("oportunidad") {
            OportunidadFormScreen(
                navController = navController
            )
        }
        // Editar una oportunidad existente (CRUD)
        composable(
            route = "oportunidad/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { entry ->
            OportunidadFormScreen(
                navController = navController,
                oportunidadId = entry.arguments?.getLong("id")
            )
        }

        composable("lista") {
            ListaOportunidadesScreen(navController)
        }

        composable("qr") {
            QrRoute(navController)
        }
        // Pide el permiso de cámara y muestra el escáner QR
        @Composable
         fun QrRoute(navController: NavController) {
            val context = LocalContext.current
            val qrViewModel: QrViewModel = viewModel()

            var tienePermiso by remember {
                mutableStateOf(CameraPermissionHelper.hasCameraPermission(context))
            }

            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { concedido -> tienePermiso = concedido }

            QrScannerScreen(
                viewModel = qrViewModel,
                hasCameraPermission = tienePermiso,
                onRequestPermission = { launcher.launch(Manifest.permission.CAMERA) },
                onOportunidadGuardada = {
                    // Después de guardar desde el QR, se muestra la lista
                    navController.navigate("lista") {
                        popUpTo("qr") { inclusive = true }
                    }
                }
            )
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
@Composable
private fun QrRoute(navController: NavController) {
    val context = LocalContext.current
    val qrViewModel: QrViewModel = viewModel()

    var tienePermiso by remember {
        mutableStateOf(CameraPermissionHelper.hasCameraPermission(context))
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido -> tienePermiso = concedido }

    QrScannerScreen(
        viewModel = qrViewModel,
        hasCameraPermission = tienePermiso,
        onRequestPermission = { launcher.launch(Manifest.permission.CAMERA) },
        onOportunidadGuardada = {
            // Después de guardar desde el QR, se muestra la lista
            navController.navigate("lista") {
                popUpTo("qr") { inclusive = true }
            }
        }
    )
}