package com.example.dsy1105_007d_2026.data.model

data class OportunidadUiState (
    val nombreOportunidad : String = "",
    val tipo : String = "",
    val descripcion : String = "",
    val fuente_de_descripcion : String = "",
    val prioridad: Prioridad? = null,
    val potencialComercial: PotencialComercial? = null,
    val observaciones : String = "",

    //Errores del formulario
    val errores : OportunidadesErrores = OportunidadesErrores()

)