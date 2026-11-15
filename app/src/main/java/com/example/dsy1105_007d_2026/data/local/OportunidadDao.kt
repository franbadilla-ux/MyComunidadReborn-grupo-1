package com.example.dsy1105_007d_2026.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OportunidadDao {

    @Insert
    suspend fun insertar(oportunidad : OportunidadEntity)

    @Query("SELECT * FROM oportinidades")
    fun getAll() : Flow<List<OportunidadEntity?>>

    @Update
    suspend fun actualizar(oportunidad : OportunidadEntity)

    @Delete
    suspend fun eliminar(oportunidad : OportunidadEntity)

    @Query("SELECT * FROM oportinidades WHERE id = :id")
    suspend fun obtenerPorId(id : Long) : OportunidadEntity?
}