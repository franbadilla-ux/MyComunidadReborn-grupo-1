package com.example.dsy1105_007d_2026.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dsy1105_007d_2026.data.model.Prioridad
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel
import com.example.dsy1105_007d_2026.data.model.Credential

@Composable
fun SupervisionScreen(viewModel: OportunidadViewModel = viewModel()) {
    val oportunidades by viewModel.oportunidades.collectAsState()
    val lista = oportunidades.filterNotNull()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Supervisión y prioridades", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Total: ${lista.size}  ·  Prioridad alta: ${lista.count { it.prioridad == Prioridad.ALTA }}")
        Spacer(Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(lista, key = { it.id }) { o ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(o.nombreOportunidad, style = MaterialTheme.typography.titleMedium)
                        Text("Estado: ${o.estado.name}")
                        Text("Prioridad: ${o.prioridad.name}")

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Prioridad.entries.forEach { p ->
                                OutlinedButton(
                                    onClick = { viewModel.cambiarPrioridad(o, p) },
                                    enabled = o.prioridad != p
                                ) {
                                    Text(p.name)
                                }
                            }
                        }
                        Text("Responsable: ${o.responsable.ifBlank { "Sin asignar" }}")

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            listOf(Credential.Ejecutivo, Credential.Supervisor).forEach { c ->
                                OutlinedButton(
                                    onClick = { viewModel.reasignar(o, c.username) },
                                    enabled = o.responsable != c.username
                                ) {
                                    Text(c.username)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}