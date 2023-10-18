package com.example.recaudacion.ui.screens

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recaudacion.network.CollectionsApi
import com.example.recaudacion.network.RegistroState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel : ViewModel() {
    var registerUiState by mutableStateOf(RegistroState())
        private set

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }

    fun getCuentaPorNroDocumento(nroDocumento: TextFieldValue) {
        viewModelScope.launch {
            try {
                val result = CollectionsApi.retrofitService.getCuenta(nroDocumento.text)
                registerUiState = registerUiState.copy(
                    idCuenta = result.idCuenta.toInt(),
                    nombre = result.nombre,
                    banco = result.banco,
                    moneda = result.moneda,
                    nroCuenta = result.nroCuenta.toLong()
                )
            } catch (e: IOException) {
                _errorMessage.value = "Error de red. Inténtelo de nuevo más tarde."
            } catch (e: HttpException) {
                if (e.code() == 500) {
                    _errorMessage.value = "El nro de documento no existe."
                } else {
                    _errorMessage.value = "Ha ocurrido un error. Inténtelo de nuevo más tarde."
                }
            }
        }
    }

    fun getPredioPorRuc(ruc: TextFieldValue) {
        viewModelScope.launch {
            try {
                val result = CollectionsApi.retrofitService.getPredio(ruc.text)
                Log.d("getPredioPorRuc"," ${result}")
                registerUiState = registerUiState.copy(
                    idCuentaPredio = result.idCuentaPredio.toInt(),
                    nombrePredio = result.nombre,
                    direccion = result.dirrecion,
                    tipoPredio = result.tipoDePredio,
                    nroCuentaPredio = result.nrCuentaPredio,
                    tipoDeAutorizacion = result.tipoAutorizacion,
                    estado = result.estado
                )
            } catch (e: IOException) {
                _errorMessage.value = "Error de red. Inténtelo de nuevo más tarde."
            } catch (e: HttpException) {
                if (e.code() == 500) {
                    _errorMessage.value = "El nro de documento no existe."
                } else {
                    _errorMessage.value = "Ha ocurrido un error. Inténtelo de nuevo más tarde."
                }
            }
        }
    }
}