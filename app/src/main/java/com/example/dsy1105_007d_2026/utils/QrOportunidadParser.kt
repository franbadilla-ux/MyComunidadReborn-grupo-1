package com.example.dsy1105_007d_2026.utils

import com.example.dsy1105_007d_2026.data.local.OportunidadEntity
import com.example.dsy1105_007d_2026.data.model.PotencialComercial
import com.example.dsy1105_007d_2026.data.model.Prioridad
import org.json.JSONException
import org.json.JSONObject

/**
 * Convierte el texto de un código QR en una oportunidad.
 *
 * El QR debe contener un JSON con este formato (observaciones es opcional):
 * {"nombre":"...","tipo":"...","descripcion":"...","fuente":"...",
 *  "prioridad":"ALTA|MEDIA|BAJA","potencial":"ALTO|MEDIO|BAJO","observaciones":"..."}
 *
 * Devuelve null si el texto no tiene ese formato.
 */
object QrOportunidadParser {

    fun parse(contenido: String): OportunidadEntity? {
        val json = try {
            JSONObject(contenido.trim())
        } catch (e: JSONException) {
            return null
        }

        val nombre = json.optString("nombre").trim()
        val tipo = json.optString("tipo").trim()
        val descripcion = json.optString("descripcion").trim()
        val fuente = json.optString("fuente").trim()
        val observaciones = json.optString("observaciones").trim()

        val prioridad = Prioridad.entries.firstOrNull {
            it.name.equals(json.optString("prioridad").trim(), ignoreCase = true)
        } ?: return null

        val potencial = PotencialComercial.entries.firstOrNull {
            it.name.equals(json.optString("potencial").trim(), ignoreCase = true)
        } ?: return null

        if (nombre.isBlank() || tipo.isBlank() || descripcion.isBlank() || fuente.isBlank()) {
            return null
        }

        return OportunidadEntity(
            nombreOportunidad = nombre,
            tipo = tipo,
            descripcion = descripcion,
            fuente_de_descripcion = fuente,
            prioridad = prioridad,
            potencialComercial = potencial,
            observaciones = observaciones
        )
    }
}