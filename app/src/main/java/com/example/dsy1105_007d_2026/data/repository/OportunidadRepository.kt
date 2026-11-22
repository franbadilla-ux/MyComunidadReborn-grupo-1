package com.example.dsy1105_007d_2026.data.repository

import com.example.dsy1105_007d_2026.data.local.HistorialDao
import com.example.dsy1105_007d_2026.data.local.HistorialEntity
import com.example.dsy1105_007d_2026.data.local.OportunidadDao
import com.example.dsy1105_007d_2026.data.local.OportunidadEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OportunidadRepository(
    private val dao: OportunidadDao,
    private val historialDao: HistorialDao
) {

    val oportunidades = dao.getAll()

    fun historial(id: Long) = historialDao.getPorOportunidad(id)

    private suspend fun registrar(id: Long, texto: String) {
        val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        historialDao.insertar(HistorialEntity(oportunidadId = id, descripcion = texto, fecha = fecha))
    }

    suspend fun insertar(oportunidad: OportunidadEntity) {
        registrar(dao.insertar(oportunidad), "Oportunidad creada")
    }

    suspend fun actualizar(nueva: OportunidadEntity) {
        val anterior = dao.obtenerPorId(nueva.id) ?: return
        dao.actualizar(nueva)

        if (anterior.estado != nueva.estado)
            registrar(nueva.id, "Estado: ${anterior.estado} → ${nueva.estado}")
        if (anterior.prioridad != nueva.prioridad)
            registrar(nueva.id, "Prioridad: ${anterior.prioridad} → ${nueva.prioridad}")
        if (anterior.observaciones != nueva.observaciones)
            registrar(nueva.id, "Resultado / observaciones actualizados")
        if (anterior.responsable != nueva.responsable)
            registrar(nueva.id, "Responsable: ${anterior.responsable.ifBlank { "Sin asignar" }} → ${nueva.responsable}")
    }

    suspend fun eliminar(oportunidad: OportunidadEntity) = dao.eliminar(oportunidad)

    suspend fun obtenerPorId(id: Long) = dao.obtenerPorId(id)
}