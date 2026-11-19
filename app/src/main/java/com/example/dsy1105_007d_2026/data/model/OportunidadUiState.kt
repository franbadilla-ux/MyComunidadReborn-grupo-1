package com.example.dsy1105_007d_2026.data.model

data class OportunidadUiState (
    val nombreOportunidad : String = "",
    val tipo : String = "",
    val descripcion : String = "",
    val fuente_de_descripcion : String = "",
    val prioridad: Prioridad? = null,
    val potencialComercial: PotencialComercial? = null,
    val observaciones : String = "",

    // Estado de la oportunidad (por defecto, el primero)
    val estado : EstadoOportunidad = EstadoOportunidad.EVENTO_IDENTIFICADO,


    // 0 = oportunidad nueva, distinto de 0 = se está editando una existente
    val id : Long = 0,

    //Errores del formulario
    val errores : OportunidadesErrores = OportunidadesErrores()

)