package com.example.dsy1105_007d_2026.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dsy1105_007d_2026.ui.qr.camera.QrResult
import com.example.dsy1105_007d_2026.ui.qr.camera.QrRepository

class QrViewModel : ViewModel() {
    private val repository = QrRepository()

    private val _qrResult = MutableLiveData<QrResult?>()
    val qrResult: LiveData<QrResult?> = _qrResult

    fun onQrDetected(content: String) {
        _qrResult.value = repository.processQrContent(content)
    }

    fun clearResult() {
        _qrResult.value = null
    }
}