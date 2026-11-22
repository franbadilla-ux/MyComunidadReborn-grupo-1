package com.example.dsy1105_007d_2026.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dsy1105_007d_2026.data.model.EstadoOportunidad
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel

@Composable
fun GestionesScreen(viewModel: OportunidadViewModel = viewModel()) {
    val oportunidades by viewModel.oportunidades.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gestiones y propuestas")

        LazyColumn {
            items(oportunidades.filterNotNull()) { o ->
                Card(Modifier.padding(top = 8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(o.nombreOportunidad)
                        Text("Estado: ${o.estado.name}")

                        Button(
                            onClick = {
                                viewModel.cambiarEstado(o, EstadoOportunidad.CONTACTO_REALIZADO)
                            }
                        ) {
                            Text("Contacto realizado")
                        }

                        Button(
                            onClick = {
                                viewModel.cambiarEstado(o, EstadoOportunidad.PROPUESTA_ENVIADA)
                            }
                        ) {
                            Text("Propuesta enviada")
                        }
                        //boton confirmar
                        Button(
                            onClick = {
                                viewModel.cambiarEstado(o, EstadoOportunidad.PARTICIPACION_CONFIRMADA)
                            }
                        ) {
                            Text("Confirmada")
                        }

                        OutlinedButton(
                            onClick = {
                                viewModel.cambiarEstado(o, EstadoOportunidad.DESCARTADA)
                            }
                        ) {
                            Text("Descartar")
                        }
                    }
                }
            }
        }
    }
}