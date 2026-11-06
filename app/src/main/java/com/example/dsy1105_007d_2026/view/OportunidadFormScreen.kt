package com.example.dsy1105_007d_2026.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsy1105_007d_2026.viewmodel.OportunidadViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dsy1105_007d_2026.data.model.PotencialComercial
import com.example.dsy1105_007d_2026.data.model.Prioridad

@Composable
fun OportunidadFormScreen(navController: NavController,
                          viewModel: OportunidadViewModel = viewModel()){
    val estado by viewModel.estado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ){
        //nombre oportunidad
        OutlinedTextField(
            value = estado.nombreOportunidad,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre Oportunidad") },
            isError = estado.errores.errorNombre != null,
            supportingText = {
                estado.errores.errorNombre?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //tipo oportunidad
        OutlinedTextField(
            value = estado.tipo,
            onValueChange = viewModel::onTipoChange,
            label = {Text("Tipo de oportunidad")},
            modifier = Modifier.fillMaxWidth()
        )
        //descripción
        OutlinedTextField(
            value = estado.descripcion,
            onValueChange = viewModel::onDescripcionChange,
            label = {Text("Descripción oportunidad")},
            modifier = Modifier.fillMaxWidth()
        )
        //Fuente de información
        OutlinedTextField(
            value = estado.fuente_de_descripcion,
            onValueChange = viewModel::onFuenteInfoChange,
            label = {Text("Fuente de la descripción")},
            modifier = Modifier.fillMaxWidth()
        )
        //prioridad

        Text("Prioridad")

        Prioridad.entries.forEach { prioridad ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = estado.prioridad == prioridad,
                    onClick = {
                        viewModel.onPrioridadChange(prioridad)
                    }
                )

                Text(prioridad.name)
            }
        }

        //Potencial comercial


        Text("Potencial comercial")

        PotencialComercial.entries.forEach { potencial ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = estado.potencialComercial == potencial,
                    onClick = {
                        viewModel.onPotencialChange(potencial)
                    }
                )

                Text(potencial.name)
            }
        }

        //observaciones

        OutlinedTextField(
            value = estado.observaciones,
            onValueChange = viewModel::onObservacionesChange,
            label = {Text("Observaciones")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.saveUrSoul {
                    navController.navigate("menu")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar oportunidad")
        }



    }
}
