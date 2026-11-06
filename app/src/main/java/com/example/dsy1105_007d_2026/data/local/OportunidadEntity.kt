package com.example.dsy1105_007d_2026.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dsy1105_007d_2026.data.model.PotencialComercial
import com.example.dsy1105_007d_2026.data.model.Prioridad

@Entity(tableName = "oportinidades")
class OportunidadEntity (
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val nombreOportunidad : String,
    val tipo : String,
    val descripcion : String,
    val fuente_de_descripcion: String,
    val prioridad : Prioridad,
    val potencialComercial : PotencialComercial,
    val observaciones : String
)