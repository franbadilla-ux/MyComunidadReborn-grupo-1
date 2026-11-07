package com.example.dsy1105_007d_2026.data.model

enum class Prioridad {
    ALTA,
    MEDIA,
    BAJA
}

data class LoginUiState(
    val username:String ="",
    val password:String ="",
    val isLoading:Boolean =false,
    val error:String? =null
)