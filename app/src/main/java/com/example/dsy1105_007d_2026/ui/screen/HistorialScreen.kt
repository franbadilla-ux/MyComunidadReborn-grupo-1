package com.example.dsy1105_007d_2026.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel

@Composable
fun HistorialScreen(id: Long, viewModel: OportunidadViewModel = viewModel()) {
    val historial by remember(id) { viewModel.historial(id) }
        .collectAsState(initial = emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial de cambios")

        if (historial.isEmpty()) Text("Sin cambios registrados")

        LazyColumn {
            items(historial) { h ->
                Card(Modifier.padding(top = 8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(h.fecha)
                        Text(h.descripcion)
                    }
                }
            }
        }
    }
}

