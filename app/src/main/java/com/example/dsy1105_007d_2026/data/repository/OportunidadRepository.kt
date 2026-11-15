package com.example.dsy1105_007d_2026.data.repository
import com.example.dsy1105_007d_2026.data.local.OportunidadDao
import com.example.dsy1105_007d_2026.data.local.OportunidadEntity

class OportunidadRepository(
    private val dao : OportunidadDao
) {

    val oportunidades = dao.getAll()

    suspend fun insertar(oportunidad: OportunidadEntity) {
        dao.insertar(oportunidad)
    }
    suspend fun actualizar(oportunidad: OportunidadEntity) {
        dao.actualizar(oportunidad)
    }

    suspend fun eliminar(oportunidad: OportunidadEntity) {
        dao.eliminar(oportunidad)
    }

    suspend fun obtenerPorId(id: Long): OportunidadEntity? {
        return dao.obtenerPorId(id)
    }
}