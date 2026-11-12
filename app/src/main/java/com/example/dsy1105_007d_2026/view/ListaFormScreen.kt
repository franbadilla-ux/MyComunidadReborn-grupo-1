package com.example.dsy1105_007d_2026.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel

@Composable
fun ListaOportunidadesScreen(
    navController: NavController,
    viewModel: OportunidadViewModel = viewModel()

) {
    val oportunidades by viewModel.oportunidades.collectAsState()

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

        Button(
            onClick = {
                navController.navigate("oportunidad")
            }
        ) {
            Text("Agregar oportunidad")
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
                        }
                    }
                }
            }
        }
    }
}