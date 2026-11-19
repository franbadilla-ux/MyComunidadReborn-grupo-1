package com.example.dsy1105_007d_2026.data.model

data class Credential (val username:String, val password:String) {
    companion object {
        val Admin = Credential(username = "admin", password = "123")
        val Ejecutivo = Credential(username = "ejecutivo", password = "2662")
        val Supervisor = Credential(username = "supervisor", password = "6226")
    }
}