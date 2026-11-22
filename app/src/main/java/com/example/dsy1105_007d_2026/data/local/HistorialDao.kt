package com.example.dsy1105_007d_2026.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialDao {

    @Insert
    suspend fun insertar(historial: HistorialEntity)

    @Query("SELECT * FROM historial WHERE oportunidadId = :id ORDER BY id DESC")
    fun getPorOportunidad(id: Long): Flow<List<HistorialEntity>>
}