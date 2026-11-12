package com.example.dsy1105_007d_2026.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsy1105_007d_2026.data.local.OportunidadDatabase
import com.example.dsy1105_007d_2026.data.local.OportunidadEntity
import com.example.dsy1105_007d_2026.data.model.OportunidadUiState
import com.example.dsy1105_007d_2026.data.model.OportunidadesErrores
import com.example.dsy1105_007d_2026.data.model.PotencialComercial
import com.example.dsy1105_007d_2026.data.model.Prioridad
import com.example.dsy1105_007d_2026.data.repository.OportunidadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OportunidadViewModel(app : Application) : AndroidViewModel(app) {

    private val dao = OportunidadDatabase.getDatabase(app).oportunidadDao()

    private val repository = OportunidadRepository(dao)

    private val _estado = MutableStateFlow(OportunidadUiState())

    val estado = _estado.asStateFlow()

    val oportunidades: StateFlow<List<OportunidadEntity?>> = repository.oportunidades
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onNombreChange(valor: String) {
        _estado.update {
            it.copy(
                nombreOportunidad = valor,
                errores = it.errores.copy(errorNombre = null)
            )
        }
    }

    fun onTipoChange(valor: String) {
        _estado.update {
            it.copy(
                tipo = valor,
                errores = it.errores.copy(errorTipo = null)
            )
        }
    }

    fun onDescripcionChange(valor: String) {
        _estado.update {
            it.copy(
                descripcion = valor,
                errores = it.errores.copy(errorDescripcion = null)
            )
        }
    }

    fun onFuenteInfoChange(valor: String) {
        _estado.update {
            it.copy(
                fuente_de_descripcion = valor,
                errores = it.errores.copy(errorFuenteInformacion = null)
            )
        }
    }

    fun onPrioridadChange(valor: Prioridad) {
        _estado.update {
            it.copy(
                prioridad = valor,
                errores = it.errores.copy(errorPrioridad = null)
            )
        }
    }

    fun onPotencialChange(valor: PotencialComercial) {
        _estado.update {
            it.copy(
                potencialComercial = valor,
                errores = it.errores.copy(errorPotencialComercial = null)
            )
        }
    }

    fun onObservacionesChange(valor: String) {
        _estado.update {
            it.copy(
                observaciones = valor,
                errores = it.errores.copy(errorObservaciones = null)
            )
        }
    }

    private fun validarFormulario(): Boolean {

        val datos = _estado.value

        val errores = OportunidadesErrores(
            errorNombre = if (datos.nombreOportunidad.isBlank())
                "Debe ingresar el nombre" else null,

            errorTipo = if (datos.tipo.isBlank())
                "Debe ingresar el tipo" else null,

            errorDescripcion = if (datos.descripcion.isBlank())
                "Debe ingresar la descripción" else null,

            errorFuenteInformacion =
                if (datos.fuente_de_descripcion.isBlank())
                    "Debe ingresar la fuente de información"
                else null,

            errorPrioridad = if (datos.prioridad == null)
                "Debe seleccionar una prioridad" else null,

            errorPotencialComercial =
                if (datos.potencialComercial == null)
                    "Debe seleccionar el potencial comercial"
                else null,
            errorObservaciones =
                if (datos.observaciones == null)
                    "Debe seleccionar las observaciones"
                else null
        )

        // Comprobar si existe algún error
        val hayErrores = listOfNotNull(
            errores.errorNombre,
            errores.errorTipo,
            errores.errorDescripcion,
            errores.errorFuenteInformacion,
            errores.errorPrioridad,
            errores.errorPotencialComercial
        ).isNotEmpty()

        // Actualizar errores en el UiState
        _estado.update {
            it.copy(errores = errores)
        }

        return !hayErrores
    }

    fun saveUrSoul(onListo: () -> Unit) {
        if (!validarFormulario()) return

        val x = _estado.value
        val prioridad = x.prioridad ?: return
        val potencialCommercial = x.potencialComercial ?: return
        viewModelScope.launch {
            repository.insertar(
                OportunidadEntity(
                    nombreOportunidad = x.nombreOportunidad.trim(),
                    tipo = x.tipo.trim(),
                    descripcion = x.descripcion.trim(),
                    fuente_de_descripcion = x.fuente_de_descripcion.trim(),
                    prioridad = prioridad,
                    potencialComercial = potencialCommercial,
                    observaciones = x.observaciones.trim()
                )
            )
            onListo()
        }
    }
}