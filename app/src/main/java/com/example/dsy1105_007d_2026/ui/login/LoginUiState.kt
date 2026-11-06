package com.example.dsy1105_007d_2026.ui.login

data class LoginUiState(
    val username:String ="",
    val password:String ="",
    val isLoading:Boolean =false,
    val error:String? =null
)