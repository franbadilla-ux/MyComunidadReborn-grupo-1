package com.example.dsy1105_007d_2026.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OportunidadDao {

    @Insert
    suspend fun insertar(oportunidad : OportunidadEntity)

    @Query("SELECT * FROM oportinidades")
    fun getAll() : Flow<List<OportunidadEntity?>>
}