package com.example.dsy1105_007d_2026.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dsy1105_007d_2026.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProductoFormScreen(
    navController: NavController,
    nombre:String,
    precio: String
){// inio Formulario
    var cantidad by remember { mutableStateOf(TextFieldValue("")) }
    var direccion by remember { mutableStateOf(TextFieldValue("")) }

    var conPapas by remember {mutableStateOf(false)}
    var agrandarBebidas by remember {mutableStateOf(false)}

    Scaffold(
        bottomBar = {
            BottomAppBar {
                TopAppBar(title={Text("Kentucky")})
            }//Transform Selected Code...
        }//fin bottom
    )//Fin scal
    {//inicio inner
        innerPadding ->
        Column(
            modifier= Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )//Fin columna
            { // Inicio contenido

            Image(
                painter= painterResource(id=R.drawable.hamurguesa),
                contentDescription = "Imagen Producto",
                modifier= Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )//fin imagen

            Spacer(Modifier.height(16.dp))
            Text(text=nombre, style= MaterialTheme.typography.headlineSmall)

                Text(text="Precio: $precio", style= MaterialTheme.typography.bodyLarge)

                Spacer(Modifier.height(16.dp))


                OutlinedTextField(
                    value=cantidad,
                    onValueChange = {cantidad=it},
                    label ={Text("Cantidad")},
                    modifier=Modifier.fillMaxWidth()
                )//fin Outlined cantidad

                OutlinedTextField(
                    value=cantidad,
                    onValueChange = {direccion=it},
                    label ={Text("Direccion")},
                    modifier=Modifier.fillMaxWidth()
                )//fin Outlined cantidad






            }// fin Inicio contenido


    }//fin inicio inner









}// fin inio Formulario

@Preview(showBackground = true)
@Composable
fun PreviewProductoFormScreen(){
    ProductoFormScreen(
        navController= rememberNavController(),
        nombre="Producto ejemplo",
        precio="$10.000"
    )

}