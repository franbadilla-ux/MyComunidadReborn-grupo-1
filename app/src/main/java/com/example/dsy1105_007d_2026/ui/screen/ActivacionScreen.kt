package com.example.dsy1105_007d_2026.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dsy1105_007d_2026.data.model.EstadoOportunidad
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel

@Composable
fun ActivacionScreen(viewModel: OportunidadViewModel = viewModel()) {
    val oportunidades by viewModel.oportunidades.collectAsState()

    val confirmadas = oportunidades.filterNotNull()
        .filter { it.estado == EstadoOportunidad.PARTICIPACION_CONFIRMADA }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gestión de activaciones")

        if (confirmadas.isEmpty()) {
            Text("No hay participaciones confirmadas")
        }

        LazyColumn {
            items(confirmadas, key = { it.id }) { o ->
                var resultado by remember { mutableStateOf(o.observaciones) }

                Card(Modifier.padding(top = 8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(o.nombreOportunidad)
                        Text("Tipo: ${o.tipo}")

                        OutlinedTextField(
                            value = resultado,
                            onValueChange = { resultado = it },
                            label = { Text("Resultado de la activación") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = { viewModel.guardarResultado(o, resultado) }
                        ) {
                            Text("Guardar resultado")
                        }
                    }
                }
            }
        }
    }
}