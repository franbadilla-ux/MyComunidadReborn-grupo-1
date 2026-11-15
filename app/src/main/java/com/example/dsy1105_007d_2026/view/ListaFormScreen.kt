package com.example.dsy1105_007d_2026.view

import androidx.compose.foundation.layout.*
//Inicio cambio
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//fin cambio
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
//Inicio cambio
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
//fin cambio
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//Inicio cambio
import androidx.lifecycle.viewmodel.compose.viewModel
//fin cambio
import androidx.navigation.NavController
//Inicio cambio
import com.example.dsy1105_007d_2026.data.local.OportunidadEntity
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel
//fin cambio

@Composable
fun ListaOportunidadesScreen(
    //Inicio cambio
    navController: NavController,
    viewModel: OportunidadViewModel = viewModel()
    //fin cambio
) {
    //Inicio cambio
    val oportunidades by viewModel.oportunidades.collectAsState()

    // Oportunidad que el usuario quiere eliminar (null = ningún diálogo abierto)
    var aEliminar by remember { mutableStateOf<OportunidadEntity?>(null) }

    // Diálogo de confirmación para eliminar
    aEliminar?.let { oportunidad ->
        AlertDialog(
            onDismissRequest = { aEliminar = null },
            title = { Text("Eliminar oportunidad") },
            text = { Text("¿Seguro que deseas eliminar \"${oportunidad.nombreOportunidad}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.eliminar(oportunidad)
                        aEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { aEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
    //fin cambio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de oportunidades",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Inicio cambio
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    navController.navigate("oportunidad")
                }
            ) {
                Text("Agregar oportunidad")
                //fin cambio
            }

            //Inicio cambio
            OutlinedButton(
                onClick = {
                    navController.navigate("qr")
                }
            ) {
                Text("Escanear QR")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val lista = oportunidades.filterNotNull()

        if (lista.isEmpty()) {
            Text("Aún no hay oportunidades registradas")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(lista, key = { it.id }) { oportunidad ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                oportunidad.nombreOportunidad,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("Tipo: ${oportunidad.tipo}")
                            Text(oportunidad.descripcion)
                            Text("Fuente: ${oportunidad.fuente_de_descripcion}")
                            Text(
                                "Prioridad: ${oportunidad.prioridad.name}  ·  " +
                                        "Potencial: ${oportunidad.potencialComercial.name}",
                                color = MaterialTheme.colorScheme.primary
                            )
                            if (oportunidad.observaciones.isNotBlank()) {
                                Text(
                                    "Obs.: ${oportunidad.observaciones}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            // Botones del CRUD: editar y eliminar
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(
                                    onClick = {
                                        navController.navigate("oportunidad/${oportunidad.id}")
                                    }
                                ) {
                                    Text("Editar")
                                }

                                TextButton(
                                    onClick = { aEliminar = oportunidad },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
            //fin cambio
        }
    }
}
